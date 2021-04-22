package com.onats.core.domain

interface DataMapper<Dto, Domain> {

    fun mapToDto(domain: Domain): Dto
    fun mapToDomain(model: Dto): Domain

    fun mapToDto(domainList: List<Domain>): List<Dto> {
        return domainList.mapTo(mutableListOf(), ::mapToDto)
    }

    fun mapToDomainList(modelList: List<Dto>): List<Domain> {
        return modelList.mapTo(mutableListOf(), ::mapToDomain)
    }

}