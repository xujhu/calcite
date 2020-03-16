package com.gerardnico.calcite;

import org.apache.calcite.runtime.CalciteContextException;
import org.apache.calcite.sql.SqlNode;
import org.apache.calcite.sql.parser.SqlParseException;
import org.apache.calcite.tools.FrameworkConfig;
import org.apache.calcite.tools.Planner;
import org.apache.calcite.tools.ValidationException;
import org.junit.Test;

public class CalciteSqlValidationTest {

    @Test
    public void parseValidationGoodTest() {
        final String sql = "select * from SALES.EMP";
        SqlNode sqlNode = CalciteSql.fromSqlToSqlNode(sql);
        CalciteSqlValidation.createCustomSqlValidator().validate(sqlNode);
    }

    @Test(expected = CalciteContextException.class)
    public void parseValidationBadTest() {
        final String sql = "select * from YOLO"; // Yolo is not a table
        SqlNode sqlNode = CalciteSql.fromSqlToSqlNode(sql);
        CalciteSqlValidation.createCustomSqlValidator().validate(sqlNode);
    }

    @Test
    public void validateFromPlanner() throws ValidationException {
        FrameworkConfig config = CalciteFramework.configScottShemaBased();
        Planner planner = CalcitePlanner.getPlannerFromFrameworkConfig(config);
        final String sql = "select * from EMP";
        SqlNode sqlNode = null;
        try {
            sqlNode = planner.parse(sql);
        } catch (SqlParseException e) {
            throw new RuntimeException(e);
        }
        planner.validate(sqlNode);

    }
}
