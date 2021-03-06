package com.gerardnico.calcite;

import org.apache.calcite.sql.SqlNode;
import org.apache.calcite.sql.parser.SqlParseException;
import org.apache.calcite.tools.FrameworkConfig;
import org.apache.calcite.tools.Planner;
import org.junit.Test;

public class CalciteSqlVisitorTest {

    @Test
    public void sqlVisitor() throws SqlParseException {
        FrameworkConfig config = CalciteFramework.configScottSchemaBased();
        Planner planner = CalcitePlanner.getPlannerFromFrameworkConfig(config);
        SqlNode sqlNode = planner.parse("select cast(deptno as varchar), count(1) from emp where SAL between 1000 and 2000 group by deptno");
        sqlNode.accept(new CalciteSqlVisitor());
    }
}
