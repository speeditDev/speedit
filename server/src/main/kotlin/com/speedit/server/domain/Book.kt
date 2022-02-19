package com.speedit.server.domain

import com.speedit.server.domain.enums.BookCategory
import org.hibernate.Hibernate
import org.hibernate.validator.constraints.ISBN
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.Table
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
    @JoinColumn(name = "category_code")
    var bookCategory: BookCategory? = null
) : BaseEntity() {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || Hibernate.getClass(this) != Hibernate.getClass(other)) return false
        other as Book

        return isbn == other.isbn
    }

    override fun hashCode(): Int = javaClass.hashCode()

    @Override
    override fun toString(): String {
        return this::class.simpleName + "(isbn = $isbn , createdAt = $createdAt , updatedAt = $updatedAt , title = $title , price = $price , link = $link , image = $image , author = $author , discount = $discount , publisher = $publisher , description = $description )"
    }

}