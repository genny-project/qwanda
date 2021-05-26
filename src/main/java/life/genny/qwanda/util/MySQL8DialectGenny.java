package life.genny.qwanda.util;

import org.hibernate.dialect.function.SQLFunctionTemplate;
import org.hibernate.dialect.function.StandardSQLFunction;
import org.hibernate.type.IntegerType;
import org.hibernate.type.StandardBasicTypes;

public class MySQL8DialectGenny extends org.hibernate.dialect.MySQL8Dialect {

   public MySQL8DialectGenny() {
       super();
       System.out.println("This is the MYSQL8 DIALECT GENNY!!!");
       registerFunction("REGEXP", new SQLFunctionTemplate(IntegerType.INSTANCE, "?1 REGEXP ?2"));
       registerFunction(
    	          "RLIKE",
    	           new StandardSQLFunction( "RLIKE", StandardBasicTypes.INTEGER )
    	      );
   }
}