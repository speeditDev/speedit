package com.speedit.server.domain

import org.hibernate.Hibernate
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.OneToMany
import javax.persistence.Table

@Entity
@Table
data class BookCategory(
    @Id
    val code:Long,
    var name:String,

    @OneToMany(mappedBy = "bookCategory")
    val bookList:List<Book> = ArrayList()
): BaseEntity() {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || Hibernate.getClass(this) != Hibernate.getClass(other)) return false
        other as BookCategory

        return code == other.code
    }

    override fun hashCode(): Int = javaClass.hashCode()

    @Override
    override fun toString(): String {
        return this::class.simpleName + "(code = $code , name = $name )"
    }
}