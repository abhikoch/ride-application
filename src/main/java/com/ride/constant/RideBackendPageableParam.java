package com.ride.constant;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiImplicitParam;

/**
 * @author Kochhar, Abhinav
 *
 */
@Target({ ElementType.METHOD, ElementType.ANNOTATION_TYPE, ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@ApiImplicitParams({
    @ApiImplicitParam(name = "page", dataType = "string", defaultValue = "0",  paramType = "query", value = "Result page you want to retrieve (0..N)"),
    @ApiImplicitParam(name = "size", dataType = "string", defaultValue = "10", paramType = "query", value = "Number of records per page."),
    @ApiImplicitParam(name = "sort", allowMultiple = false, defaultValue="walletId,asc", dataType = "string", paramType = "query", value = "Sorting criteria in the format: property(,asc|desc).") })
public @interface RideBackendPageableParam {
}