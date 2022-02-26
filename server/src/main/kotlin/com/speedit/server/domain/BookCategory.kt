package com.speedit.server.domain

import org.hibernate.Hibernate
import javax.persistence.*

@Entity
@Table(indexes = [
    Index(name = "depth0", columnList = "depth0"),
    Index(name = "depth1", columnList = "depth1"),
    Index(name = "depth2", columnList = "depth2"),
    Index(name = "depth3", columnList = "depth3"),
    Index(name = "depth4", columnList = "depth4"),
    Index(name = "depth5", columnList = "depth5"),
])
data class BookCategory(
    @Id
    val code:Long,
    var name:String,

    @OneToMany(mappedBy = "bookCategory")
    val bookList:List<Book> = ArrayList()
): BaseEntity() {
    lateinit var depth0:String
    lateinit var depth1:String
    lateinit var depth2:String
    lateinit var depth3:String
    lateinit var depth4:String
    lateinit var depth5:String

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