// Copyright 2018 HP Development Company, L.P.
// SPDX-License-Identifier: MIT
//
// DO NOT MODIFY. Code is auto-generated by genTypes.py. Content taken from registry at
// https://www.iana.org/assignments/ipp-registrations/ipp-registrations.xml, updated on 2020-02-20
@file:Suppress("MaxLineLength", "WildcardImport")

package com.hp.jipp.model

import com.hp.jipp.encoding.* // ktlint-disable no-wildcard-imports

/**
 * Data object corresponding to a "job-impressions-col" collection as defined in:
 * [APRIL2015F2F](https://ftp.pwg.org/pub/pwg/ipp/minutes/ippv2-f2f-minutes-20150429.pdf).
 */
@Suppress("RedundantCompanionReference", "unused")
data class JobImpressionsCol
constructor(
    var blank: Int? = null,
    var blankTwoSided: Int? = null,
    var fullColor: Int? = null,
    var fullColorTwoSided: Int? = null,
    var highlightColor: Int? = null,
    var highlightColorTwoSided: Int? = null,
    var monochrome: Int? = null,
    var monochromeTwoSided: Int? = null
) : AttributeCollection {

    /** Construct an empty [JobImpressionsCol]. */
    constructor() : this(null, null, null, null, null, null, null, null)

    /** Produce an attribute list from members. */
    override val attributes: List<Attribute<*>> by lazy {
        listOfNotNull(
            blank?.let { Types.blank.of(it) },
            blankTwoSided?.let { Types.blankTwoSided.of(it) },
            fullColor?.let { Types.fullColor.of(it) },
            fullColorTwoSided?.let { Types.fullColorTwoSided.of(it) },
            highlightColor?.let { Types.highlightColor.of(it) },
            highlightColorTwoSided?.let { Types.highlightColorTwoSided.of(it) },
            monochrome?.let { Types.monochrome.of(it) },
            monochromeTwoSided?.let { Types.monochromeTwoSided.of(it) }
        )
    }

    /** Types for each member attribute. */
    object Types {
        @JvmField val blank = IntType("blank")
        @JvmField val blankTwoSided = IntType("blank-two-sided")
        @JvmField val fullColor = IntType("full-color")
        @JvmField val fullColorTwoSided = IntType("full-color-two-sided")
        @JvmField val highlightColor = IntType("highlight-color")
        @JvmField val highlightColorTwoSided = IntType("highlight-color-two-sided")
        @JvmField val monochrome = IntType("monochrome")
        @JvmField val monochromeTwoSided = IntType("monochrome-two-sided")
    }

    /** Defines types for each member of [JobImpressionsCol] */
    companion object : AttributeCollection.Converter<JobImpressionsCol> {
        override fun convert(attributes: List<Attribute<*>>): JobImpressionsCol =
            JobImpressionsCol(
                extractOne(attributes, Types.blank),
                extractOne(attributes, Types.blankTwoSided),
                extractOne(attributes, Types.fullColor),
                extractOne(attributes, Types.fullColorTwoSided),
                extractOne(attributes, Types.highlightColor),
                extractOne(attributes, Types.highlightColorTwoSided),
                extractOne(attributes, Types.monochrome),
                extractOne(attributes, Types.monochromeTwoSided)
            )
    }
    override fun toString() = "JobImpressionsCol(${attributes.joinToString()})"
}
