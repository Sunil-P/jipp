// Copyright 2018 HP Development Company, L.P.
// SPDX-License-Identifier: MIT
//
// DO NOT MODIFY. Code is auto-generated by genTypes.py. Content taken from registry at
// https://www.iana.org/assignments/ipp-registrations/ipp-registrations.xml, updated on 2020-02-20
@file:Suppress("MaxLineLength", "WildcardImport")

package com.hp.jipp.model

import com.hp.jipp.encoding.* // ktlint-disable no-wildcard-imports

/**
 * Data object corresponding to a "separator-sheets" collection as defined in:
 * [PWG5100.3](https://ftp.pwg.org/pub/pwg/candidates/cs-ippprodprint10-20010212-5100.3.pdf),
 * [RFC8011](http://www.iana.org/go/rfc8011).
 */
@Suppress("RedundantCompanionReference", "unused")
data class SeparatorSheets
constructor(
    /** May contain any keyword from [Media] or a name. */
    var media: KeywordOrName? = null,
    var mediaCol: MediaCol? = null,
    /** May contain any keyword from [SeparatorSheetsType]. */
    var separatorSheetsType: List<String>? = null
) : AttributeCollection {

    /** Construct an empty [SeparatorSheets]. */
    constructor() : this(null, null, null)

    /** Produce an attribute list from members. */
    override val attributes: List<Attribute<*>> by lazy {
        listOfNotNull(
            media?.let { Types.media.of(it) },
            mediaCol?.let { Types.mediaCol.of(it) },
            separatorSheetsType?.let { Types.separatorSheetsType.of(it) }
        )
    }

    /** Types for each member attribute. */
    object Types {
        @JvmField val media = KeywordOrNameType("media")
        @JvmField val mediaCol = AttributeCollection.Type("media-col", MediaCol)
        @JvmField val separatorSheetsType = KeywordType.Set("separator-sheets-type")
    }

    /** Defines types for each member of [SeparatorSheets] */
    companion object : AttributeCollection.Converter<SeparatorSheets> {
        override fun convert(attributes: List<Attribute<*>>): SeparatorSheets =
            SeparatorSheets(
                extractOne(attributes, Types.media),
                extractOne(attributes, Types.mediaCol),
                extractAll(attributes, Types.separatorSheetsType)
            )
    }
    override fun toString() = "SeparatorSheets(${attributes.joinToString()})"
}
