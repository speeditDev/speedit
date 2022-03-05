package com.speedit.server.domain

import javax.persistence.*

@Entity
@Table
class FavoriteBook(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,

    @ManyToOne
    @JoinColumn(name = "userId", foreignKey = ForeignKey(name = "FK_FAVORITE_BOOK_TO_USER"))
    val user: User,

    @OneToOne
    @JoinColumn(name = "isbn", foreignKey = ForeignKey(name = "FK_FAVORITE_BOOK_TO_BOOK"))
    val book: Book

): BaseEntity(){
}