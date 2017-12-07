package com.wqh.blog.handle;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;

import java.io.ByteArrayInputStream;
import java.io.UnsupportedEncodingException;
import java.sql.*;

/**
 * @author wanqh
 * @date 2017/12/07 16:52
 * @description: 自定义的typehandle，将Blob转化为String
 */
@MappedJdbcTypes(JdbcType.BLOB)
public class ConvertBlobTypeHandler extends BaseTypeHandler<String> {


    private static final String DEFAULT_CHARSET = "utf-8";

    @Override
    public void setNonNullParameter(PreparedStatement preparedStatement, int i, String parameter, JdbcType jdbcType) throws SQLException {
        ByteArrayInputStream bis;
        try {
            bis = new ByteArrayInputStream(parameter.getBytes(DEFAULT_CHARSET));
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("Blob Encoding Error!");
        }
        preparedStatement.setBinaryStream(i,bis,parameter.length());
    }

    @Override
    public String getNullableResult(ResultSet resultSet, String s) throws SQLException {
        Blob blob = resultSet.getBlob(s);
        byte[] resultValue = null;
        if(null != blob){
            resultValue = blob.getBytes(1, (int) blob.length());
        }
        try {
            return new String(resultValue, DEFAULT_CHARSET);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("Blob Encoding Error!");
        }
    }

    @Override
    public String getNullableResult(ResultSet resultSet, int i) throws SQLException {
        return null;
    }

    @Override
    public String getNullableResult(CallableStatement callableStatement, int columnIndex) throws SQLException {
        Blob blob = callableStatement.getBlob(columnIndex);
        byte[] returnValue = null;
        if (null != blob) {
            returnValue = blob.getBytes(1, (int) blob.length());
        }
        try {
            return new String(returnValue, DEFAULT_CHARSET);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("Blob Encoding Error!");
        }
    }
}
