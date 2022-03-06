package com.speedit.server.dto.book.speedit

import com.speedit.server.domain.Book
import com.speedit.server.dto.book.aladin.AladinBookDto
import org.springframework.beans.BeanUtils

@Suppress("unused")
class BookDto{
    var isbn: Long = 0
    val title: String = ""
    val price: Long = 0
    var link: String = ""
    var image: String = ""
    var author: String = ""
    var discount: Long = 0
    var publisher: String = ""
    var description: String = ""
    var categoryCode: Long = 0

    companion object {
        fun of(book: Book): BookDto {
            val bookDto = BookDto()
            BeanUtils.copyProperties(book, bookDto)
            return bookDto
        }

        fun of(aladinBookDto: AladinBookDto) {
            val bookDto = BookDto()
            BeanUtils.copyProperties(aladinBookDto, bookDto)
            bookDto.isbn = aladinBookDto.isbn13.toLong()
            bookDto.image = aladinBookDto.cover
            bookDto.discount = aladinBookDto.priceSales
            bookDto.categoryCode = aladinBookDto.categoryId
        }
    }

}