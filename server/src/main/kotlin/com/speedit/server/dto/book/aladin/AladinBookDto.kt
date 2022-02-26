package com.speedit.server.dto.book.aladin

import org.apache.commons.lang3.builder.ToStringBuilder
import org.apache.commons.lang3.builder.ToStringStyle
import org.apache.commons.text.StringEscapeUtils.unescapeJava

@Suppress("unused")
class AladinBookDto {
    lateinit var title: String
    lateinit var link: String
    lateinit var author: String
    lateinit var pubDate: String
    lateinit var description: String
    lateinit var isbn: String
    lateinit var isbn13: String
    var itemId: Long = 0
    var priceSales: Long = 0
    var priceStandard: Long = 0
    lateinit var mallType: String
    lateinit var stockStatus: String
    var mileage: Long = 0
    lateinit var cover: String
    var categoryId: Long = 0
    lateinit var categoryName: String
    lateinit var publisher: String
    var salesPoint: Long = 0
    var adult: Boolean = false
    var fixedPrice: Boolean = true
    var customerReviewRank: Long = 0

    override fun toString(): String {
        return unescapeJava(ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE))
    }
}