package org.wordpress.emailchecker2

import java.util.*

private val sModel =
        setOf("yahoo.com", "google.com", "hotmail.com", "gmail.com", "me.com", "aol.com", "mac.com", "live.com",
                "comcast.net", "comcast.com", "googlemail.com", "msn.com", "hotmail.co.uk", "yahoo.co.uk",
                "facebook.com", "verizon.net", "sbcglobal.net", "att.net", "gmx.com", "mail.com", "outlook.com",
                "ymail.com")

fun suggestDomainCorrection(email: String): String {
    val splitEmail = email.split("@")
    if (splitEmail.size < 2) {
        return email
    }
    // If the domain name is empty, don't try to suggest anything
    val domain = splitEmail[1]
    if (domain.isBlank()) {
        return email
    }
    // If the domain name is too long, don't try suggestion (resource consuming and useless)
    val maxLength = sModel.maxBy { s -> s.length }?.length ?: 0
    if (domain.length > maxLength + 1) {
        return email
    }

    // Try to find a suggestion
    val suggestedDomain = suggest(domain)
    if (suggestedDomain.isBlank()) {
        return email
    }

    // Suggestion found, return suggested address
    val prefix = splitEmail[0]
    return prefix + "@" + suggestedDomain
}


private fun suggest(word: String): String {
    // Check if the word is mispelled
    if (sModel.contains(word)) {
        return word
    }

    val candidates = known(edits(word))
    if (candidates.isEmpty()) {
        return ""
    }
    return candidates.first()
}

private fun edits(word: String): List<String> {
    val edits = ArrayList<String>()
    // deletes
    for (i in word.indices) {
        edits.add(word.substring(0, i) + word.substring(i + 1))
    }
    // transposes
    for (i in 0..word.length - 2) {
        edits.add(word.substring(0, i) + word[i + 1] + word[i] + word.substring(i + 2))
    }
    // replaces
    for (i in word.indices) {
        for (c in 0..25) {
            edits.add(word.substring(0, i) + ('a' + c).toChar() + word.substring(i + 1))
        }
    }
    // inserts
    for (i in word.indices) {
        for (c in 0..25) {
            edits.add(word.substring(0, i) + ('a' + c).toChar()  + word.substring(i))
        }
    }
    return edits
}

private fun known(words: List<String>): List<String> {
    return words.filter { word -> word in sModel }
}
