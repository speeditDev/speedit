package com.speedit.server.domain

import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.OneToMany
import javax.persistence.Table

@Entity
@Table
class SpeeditBookCategory(
    @Id
    val id: Long,
    var name: String
): BaseEntity() {
    @OneToMany(mappedBy = "speeditBookCategory")
    lateinit var bookCategoryList: List<BookCategory>

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as SpeeditBookCategory

        if (id != other.id) return false
        if (name != other.name) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id.hashCode()
        result = 31 * result + name.hashCode()
        return result
    }

    override fun toString(): String {
        return "SpeeditBookCategory(id=$id, name='$name')"
    }

}