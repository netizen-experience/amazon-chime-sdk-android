package com.amazonaws.services.chime.sdkdemo.data

import com.google.gson.annotations.SerializedName

data class ChatHistoryResponse(
    @SerializedName("messages") val messages: List<HistoryMessage>
)

data class HistoryMessage(
    @SerializedName("Content") val content: String,
    @SerializedName("ContentType") val contentType: String,
    @SerializedName("CreatedTimestamp") val createdTimestamp: String,
    @SerializedName("LastEditedTimestamp") val lastEditedTimestamp: String,
    @SerializedName("LastUpdatedTimestamp") val lastUpdatedTimestamp: String,
    @SerializedName("MessageAttributes") val messageAttributes: Map<String, MessageAttributeValue>,
    @SerializedName("MessageId") val messageId: String,
    @SerializedName("Metadata") val metadata: String,
    @SerializedName("Redacted") val redacted: Boolean,
    @SerializedName("Sender") val sender: Sender,
    @SerializedName("Status") val status: Status,
    @SerializedName("Target") val target: List<TargetMember>,
    @SerializedName("Type") val type: String
)

data class MessageAttributeValue(
    @SerializedName("StringValues") val stringValues: List<String>
)

data class Sender(
    @SerializedName("Arn") val arn: String,
    @SerializedName("Name") val name: String
)

data class Status(
    @SerializedName("Detail") val detail: String,
    @SerializedName("Value") val value: String
)

data class TargetMember(
    @SerializedName("MemberArn") val memberArn: String
)
