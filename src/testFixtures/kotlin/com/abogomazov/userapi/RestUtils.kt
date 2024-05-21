package com.abogomazov.userapi

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.test.web.servlet.MvcResult

inline fun <reified T> MvcResult.toType() = ObjectMapper().readValue(this.response.contentAsString, T::class.java)

inline fun <reified T> T.toJsonString() = ObjectMapper().writeValueAsString(this)
