package com.speedit.server.domain

import javax.persistence.*

@Entity
@Table
class Feed(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,
    @OneToMany(cascade = [CascadeType.ALL], mappedBy = "feed", fetch = FetchType.LAZY)
    val sentenceList: MutableSet<Sentence> = mutableSetOf()
): BaseEntity() {
    @ManyToOne
    @JoinColumn(name = "userId", foreignKey = ForeignKey(name = "FK_FEED_TO_USER"))
    lateinit var creator: User


    fun addSentence(sentence: Sentence) {
        sentenceList.add(sentence)
        sentence.feed = this
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Feed

        if (id != other.id) return false

        return true
    }

    override fun hashCode(): Int {
        return id.hashCode()
    }

    override fun toString(): String {
//        return "Feed(id=$id, sentenceList=${sentenceList.stream().map(Sentence::id).collect(Collectors.toList())})"
        return "Feed(id=$id)"
    }

}