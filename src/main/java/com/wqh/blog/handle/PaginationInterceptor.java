package com.wqh.blog.handle;

import com.wqh.blog.dialect.Dialect;
import com.wqh.blog.dialect.SQLHelp;
import com.wqh.blog.dialect.db.MySqlDialect;
import com.wqh.blog.domain.Page;
import com.wqh.blog.util.ReflectHelper;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlSource;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;

/**
 * @author wanqh
 * @date 2017/12/07 14:54
 * @description: mybatis分页插件
 */
@Intercepts({
        @Signature(method = "query", type = Executor.class, args = {
                MappedStatement.class, Object.class, RowBounds.class,
                ResultHandler.class})
})
public class PaginationInterceptor implements Interceptor {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    protected static final String PAGE = "page";

    private Dialect dialect;


    /**
     * 分页拦截方法
     * @param invocation
     * @return
     * @throws Throwable
     */
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        final MappedStatement mappedStatement = (MappedStatement) invocation.getArgs()[0];
        //拦截需要分页的SQL
        Object parameter = invocation.getArgs()[1];
        BoundSql boundSql = mappedStatement.getBoundSql(parameter);
        Object parameterObject = boundSql.getParameterObject();
        //获取分页参数对象
        Page<Object> page = null;
        if(parameterObject != null){
            page = convertParameter(parameterObject,page);
        }
        //如果设置了分页对象，则进行分页
        if(page != null && page.getPageSize() != -1){
            if(StringUtils.isBlank(boundSql.getSql())){
                return null;
            }
            String origin_sql = boundSql.getSql().trim();

            //设置总的记录数
            page.setCount(SQLHelp.getRowCount(origin_sql,null,boundSql,mappedStatement,parameterObject));
            //分页查询
            String pageSql = SQLHelp.generatePageSql(origin_sql,page,dialect);
            invocation.getArgs()[2] = new RowBounds(RowBounds.NO_ROW_OFFSET,RowBounds.NO_ROW_LIMIT);
            BoundSql newBoundSql = new BoundSql(mappedStatement.getConfiguration(),
                                                        pageSql,
                                                        boundSql.getParameterMappings(),
                                                        boundSql.getParameterObject());
            MappedStatement newMs = copyFromMappedStatement(mappedStatement,new BoundSqlSqlSource(newBoundSql));

            invocation.getArgs()[0] = newMs;
        }
        return invocation.proceed();
    }

    private MappedStatement copyFromMappedStatement(MappedStatement ms,
                                                    SqlSource newSqlSource) {
        MappedStatement.Builder builder = new MappedStatement.Builder(ms.getConfiguration(),
                ms.getId(), newSqlSource, ms.getSqlCommandType());
        builder.resource(ms.getResource());
        builder.fetchSize(ms.getFetchSize());
        builder.statementType(ms.getStatementType());
        builder.keyGenerator(ms.getKeyGenerator());
        if (ms.getKeyProperties() != null) {
            for (String keyProperty : ms.getKeyProperties()) {
                builder.keyProperty(keyProperty);
            }
        }
        builder.timeout(ms.getTimeout());
        builder.parameterMap(ms.getParameterMap());
        builder.resultMaps(ms.getResultMaps());
        builder.cache(ms.getCache());
        return builder.build();
    }

    public static class BoundSqlSqlSource implements SqlSource {
        BoundSql boundSql;

        public BoundSqlSqlSource(BoundSql boundSql) {
            this.boundSql = boundSql;
        }

        @Override
        public BoundSql getBoundSql(Object parameterObject) {
            return boundSql;
        }
    }

    /**
     * 对参数进行转换和检查
     * @param parameterObject 参数对象
     * @param page            分页对象
     * @return 分页对象
     * @throws NoSuchFieldException 无法找到参数
     */
    @SuppressWarnings("unchecked")
    protected static Page<Object> convertParameter(Object parameterObject, Page<Object> page) {
        try{
            if (parameterObject instanceof Page) {
                return (Page<Object>) parameterObject;
            } else {
                return (Page<Object>) ReflectHelper.getFieldValue(parameterObject, PAGE);
            }
        }catch (Exception e) {
            return null;
        }
    }




    @Override
    public Object plugin(Object o) {
        return Plugin.wrap(o, this);
    }

    @Override
    public void setProperties(Properties properties) {
        dialect = new MySqlDialect();
    }

}
