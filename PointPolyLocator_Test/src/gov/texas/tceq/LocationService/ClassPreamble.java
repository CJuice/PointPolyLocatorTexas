package gov.texas.tceq.LocationService;

import java.lang.annotation.Documented;

@Documented
public @interface ClassPreamble {
		String author() default "CSchaefe";
		String date() default "08/24/17";
}
