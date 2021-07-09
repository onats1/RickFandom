package com.onats.common

interface Mapper<Dto, Domain> {
    fun mapToDto(domain: Domain): Dto
    fun mapToDomain(model: Dto): Domain

    fun mapToDtoList(domainList: List<Domain>?): List<Dto> {
        return domainList?.mapTo(mutableListOf(), ::mapToDto)?: mutableListOf()
    }

    fun mapToDomainList(modelList: List<Dto>?): List<Domain> {
        return modelList?.mapTo(mutableListOf(), ::mapToDomain)?: mutableListOf()
    }
}