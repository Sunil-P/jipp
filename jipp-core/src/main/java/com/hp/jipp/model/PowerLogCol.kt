// Copyright 2018 HP Development Company, L.P.
// SPDX-License-Identifier: MIT
//
// DO NOT MODIFY. Code is auto-generated by genTypes.py. Content taken from registry at
// https://www.iana.org/assignments/ipp-registrations/ipp-registrations.xml, updated on 2020-02-20
@file:Suppress("MaxLineLength", "WildcardImport")

package com.hp.jipp.model

import com.hp.jipp.encoding.* // ktlint-disable no-wildcard-imports

/**
 * Data object corresponding to a "power-log-col" collection as defined in:
 * [PWG5100.22](https://ftp.pwg.org/pub/pwg/candidates/cs-ippsystem10-20191122-5100.22.pdf).
 */
@Suppress("RedundantCompanionReference", "unused")
data class PowerLogCol
constructor(
    var logId: Int? = null,
    var powerState: String? = null,
    var powerStateDateTime: java.util.Calendar? = null,
    var powerStateMessage: String? = null
) : AttributeCollection {

    /** Construct an empty [PowerLogCol]. */
    constructor() : this(null, null, null, null)

    /** Produce an attribute list from members. */
    override val attributes: List<Attribute<*>> by lazy {
        listOfNotNull(
            logId?.let { Types.logId.of(it) },
            powerState?.let { Types.powerState.of(it) },
            powerStateDateTime?.let { Types.powerStateDateTime.of(it) },
            powerStateMessage?.let { Types.powerStateMessage.of(it) }
        )
    }

    /** Types for each member attribute. */
    object Types {
        @JvmField val logId = IntType("log-id")
        @JvmField val powerState = KeywordType("power-state")
        @JvmField val powerStateDateTime = DateTimeType("power-state-date-time")
        @JvmField val powerStateMessage = TextType("power-state-message")
    }

    /** Defines types for each member of [PowerLogCol] */
    companion object : AttributeCollection.Converter<PowerLogCol> {
        override fun convert(attributes: List<Attribute<*>>): PowerLogCol =
            PowerLogCol(
                extractOne(attributes, Types.logId),
                extractOne(attributes, Types.powerState),
                extractOne(attributes, Types.powerStateDateTime),
                extractOne(attributes, Types.powerStateMessage)?.value
            )
    }
    override fun toString() = "PowerLogCol(${attributes.joinToString()})"
}
