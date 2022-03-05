package com.speedit.server.domain

import javax.persistence.*

@Entity
@Table
class Sentence(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,

    @Lob
    @Column(length = 1000)
    var sentence: String,

    @OneToOne
    @JoinColumn(name = "isbn", foreignKey = ForeignKey(name = "FK_SENTENCE_TO_BOOK"))
    var book: Book,

): BaseEntity() {
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "feedId", foreignKey = ForeignKey(name = "FK_SENTENCE_TO_FEED"))
    lateinit var feed: Feed

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Sentence

        if (id != other.id) return false
        if (sentence != other.sentence) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id.hashCode()
        result = 31 * result + sentence.hashCode()
        return result
    }

    override fun toString(): String {
        return "Sentence(id=$id, sentence='$sentence', isbn=${book.isbn})"
    }
}