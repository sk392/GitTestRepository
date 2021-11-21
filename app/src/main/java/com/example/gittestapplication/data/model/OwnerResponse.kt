package com.example.gittestapplication.data.model

import com.example.gittestapplication.domain.model.Owner
import com.google.gson.annotations.SerializedName

data class OwnerResponse(
    @SerializedName("id") val id: Int?,
    @SerializedName("login") val login: String?,
    @SerializedName("url") val url: String?,
    @SerializedName("avatar_url") val avatarUrl: String?,
    @SerializedName("events_url") val eventsUrl: String?,
    @SerializedName("followers_url") val followersUrl: String?,
    @SerializedName("following_url") val followingUrl: String?,
    @SerializedName("gists_url") val gistsUrl: String?,
    @SerializedName("gravatar_id") val gravatarId: String?,
    @SerializedName("html_url") val htmlUrl: String?,
    @SerializedName("node_id") val nodeId: String?,
    @SerializedName("organizations_url") val organizationsUrl: String?,
    @SerializedName("receivedEvents_url") val receivedEventsUrl: String?,
    @SerializedName("repos_url") val reposUrl: String?,
    @SerializedName("site_admin") val siteAdmin: Boolean?,
    @SerializedName("starred_url") val starredUrl: String?,
    @SerializedName("subscriptions_url") val subscriptionsUrl: String?,
    @SerializedName("type") val type: String?
)

fun OwnerResponse.toEntity(): Owner? {
    return Owner(
        id ?: return null,
        login ?: return null,
        url ?: return null,
        avatarUrl,
        eventsUrl,
        followersUrl,
        followingUrl,
        gistsUrl,
        gravatarId,
        htmlUrl,
        nodeId,
        organizationsUrl,
        receivedEventsUrl,
        reposUrl,
        siteAdmin,
        starredUrl,
        subscriptionsUrl,
        type
    )
}