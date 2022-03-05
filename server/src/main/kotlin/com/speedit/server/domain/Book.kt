package com.speedit.server.domain

import com.speedit.server.dto.book.aladin.AladinBookDto
import org.hibernate.Hibernate
import org.hibernate.validator.constraints.ISBN
import org.springframework.beans.BeanUtils
import javax.persistence.*
import javax.validation.constraints.PositiveOrZero

@Entity
@Table
data class Book(
    @ISBN @Id
    val isbn: Long,
    val title: String,

    @PositiveOrZero
    val price: Long,

    var link: String?,
    var image: String?,
    var author: String?,
    var discount: Long = 0,
    var publisher: String?,
    var description: String = "",

    @ManyToOne
    @JoinColumn(name = "categoryCode", foreignKey = ForeignKey(name = "FK_BOOK_TO_BOOK_CATEGORY"))
    var bookCategory: BookCategory? = null
) : BaseEntity() {

    companion object {
        fun of(aladinBookDto: AladinBookDto): Book {
            val book = Book(aladinBookDto.isbn13.toLong(), aladinBookDto.title, aladinBookDto.priceStandard, aladinBookDto.link, aladinBookDto.cover, aladinBookDto.author, aladinBookDto.priceSales, aladinBookDto.publisher, aladinBookDto.description, BookCategory(aladinBookDto.categoryId, aladinBookDto.categoryName))
            BeanUtils.copyProperties(aladinBookDto, book)

            return book
        }
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || Hibernate.getClass(this) != Hibernate.getClass(other)) return false
        other as Book

        return isbn == other.isbn
    }

    override fun hashCode(): Int = javaClass.hashCode()

    override fun toString(): String {
        return this::class.simpleName + "(isbn=$isbn, title='$title', price=$price, link=$link, image=$image, author=$author, discount=$discount, publisher=$publisher, description='$description', bookCategory=$bookCategory)"
    }
}