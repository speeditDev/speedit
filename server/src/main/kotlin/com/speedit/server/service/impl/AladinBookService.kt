package com.speedit.server.service.impl

import com.speedit.server.config.api.AladinConfig
import com.speedit.server.domain.Book
import com.speedit.server.dto.book.aladin.AladinBookListDto
import com.speedit.server.repository.jpa.BookCategoryRepository
import com.speedit.server.repository.jpa.BookRepository
import com.speedit.server.service.BookService
import org.apache.commons.text.StringEscapeUtils.unescapeJava
import org.slf4j.LoggerFactory
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.Pageable
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpMethod
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import org.springframework.util.MultiValueMap
import org.springframework.web.client.RestTemplate
import java.util.stream.Collectors

@Service(value = "aladinBookService")
class AladinBookService: BookService {
    lateinit var bookRepository: BookRepository
    lateinit var bookCategoryRepository: BookCategoryRepository
    lateinit var restTemplate: RestTemplate
    lateinit var aladinConfig: AladinConfig
    private val log = LoggerFactory.getLogger(javaClass)

    @Suppress("unused")
    enum class QueryType {
        ItemNewAll,
        ItemNewSpecial,
        ItemEditorChoice,
        Bestseller,
        BlogBest
    }

    override fun searchBookByCategory(bookCategoryCode: Long, pageable: Pageable): Page<Book> {
        val uriVarargs = HashMap<String, String>()
        uriVarargs["ttbKey"] = aladinConfig.ttbKey
        uriVarargs["version"] = aladinConfig.apiVersion
        uriVarargs["queryType"] = QueryType.ItemNewAll.name
        uriVarargs["maxResults"] = pageable.pageSize.toString()
        uriVarargs["start"] = pageable.pageNumber.toString()
        uriVarargs["searchTarget"] = "Book"
        uriVarargs["output"] = "js"
        uriVarargs["categoryId"] = bookCategoryCode.toString()

        val url = "${aladinConfig.baseUrl}/ItemList.aspx?" +
                "ttbkey={ttbKey}&QueryType={queryType}&MaxResults={maxResults}&start={start}&SearchTarget={searchTarget}&output={output}&Version={version}&CategoryId={categoryId}"

        log.info(url)

        val httpHeaders = HttpHeaders()
        httpHeaders["Content-Type"] = org.springframework.http.MediaType.APPLICATION_JSON_VALUE
        val httpEntity: HttpEntity<MultiValueMap<String, String>> = HttpEntity(httpHeaders)

        val response: ResponseEntity<AladinBookListDto> = restTemplate.exchange(
            url, HttpMethod.GET, httpEntity, AladinBookListDto::class.java, uriVarargs)

        log.info(unescapeJava(response.toString()))

        val bookDtoList = response.body!!.item.stream()
            .map { value ->
                Book.of(value)
            }.collect(Collectors.toList())

        return PageImpl(bookDtoList)
    }

    override fun searchBookByTitle(title: String, pageable: Pageable): Page<Book> {
        TODO("Not yet implemented")
    }

    override fun searchBookByIsbn(isbn: Long) {
        TODO("Not yet implemented")
    }
}