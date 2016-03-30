@file:JvmName("EmailChecker")

package org.wordpress.emailchecker2

import java.util.*

// https://github.com/mailcheck/mailcheck/wiki/List-of-Popular-Domains
private val sModel =
        sortedSetOf(
                /* Default domains included */
                "aol.com", "att.net", "comcast.net", "facebook.com", "gmail.com", "gmx.com", "googlemail.com",
                "google.com", "hotmail.com", "hotmail.co.uk", "mac.com", "me.com", "msn.com",
                "live.com", "sbcglobal.net", "verizon.net", "yahoo.com", "yahoo.co.uk",

                /* Other global domains */
                "games.com" /* AOL */, "gmx.net", "hush.com", "hushmail.com", "icloud.com", "inbox.com",
                "lavabit.com", "love.com" /* AOL */, "outlook.com", "pobox.com", "rocketmail.com" /* Yahoo */,
                "safe-mail.net", "wow.com" /* AOL */, "ygm.com" /* AOL */, "ymail.com" /* Yahoo */, "zoho.com", "fastmail.fm",
                "yandex.com",

                /* United States ISP domains */
                "bellsouth.net", "charter.net", "comcast.com", "cox.net", "earthlink.net", "juno.com",

                /* British ISP domains */
                "btinternet.com", "virginmedia.com", "blueyonder.co.uk", "freeserve.co.uk", "live.co.uk",
                "ntlworld.com", "o2.co.uk", "orange.net", "sky.com", "talktalk.co.uk", "tiscali.co.uk",
                "virgin.net", "wanadoo.co.uk", "bt.com",

                /* Domains used in Asia */
                "sina.com", "qq.com", "naver.com", "hanmail.net", "daum.net", "nate.com", "yahoo.co.jp", "yahoo.co.kr", "yahoo.co.id", "yahoo.co.in", "yahoo.com.sg", "yahoo.com.ph",

                /* French ISP domains */
                "hotmail.fr", "live.fr", "laposte.net", "yahoo.fr", "wanadoo.fr", "orange.fr", "gmx.fr", "sfr.fr", "neuf.fr", "free.fr",

                /* German ISP domains */
                "gmx.de", "hotmail.de", "live.de", "online.de", "t-online.de" /* T-Mobile */, "web.de", "yahoo.de",

                /* Russian ISP domains */
                "mail.ru", "rambler.ru", "yandex.ru", "ya.ru", "list.ru",

                /* Belgian ISP domains */
                "hotmail.be", "live.be", "skynet.be", "voo.be", "tvcablenet.be", "telenet.be",

                /* Argentinian ISP domains */
                "hotmail.com.ar", "live.com.ar", "yahoo.com.ar", "fibertel.com.ar", "speedy.com.ar", "arnet.com.ar",

                /* Domains used in Mexico */
                "hotmail.com", "gmail.com", "yahoo.com.mx", "live.com.mx", "yahoo.com", "hotmail.es", "live.com", "hotmail.com.mx", "prodigy.net.mx", "msn.com"
        )

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
    // last char insert
    for (c in 0..25) {
        edits.add(word + ('a' + c).toChar())
    }

    return edits
}

private fun known(words: List<String>): List<String> {
    return words.filter { word -> word in sModel }
}
