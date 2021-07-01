package com.github.hoshikurama.componentDSL

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.TextComponent
import net.kyori.adventure.text.event.ClickEvent
import net.kyori.adventure.text.event.HoverEvent
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer

fun TextComponent.Builder.formattedContent(content: String) {
    content("")
    append(LegacyComponentSerializer.legacyAmpersand().deserialize(content))
}

fun <T> TextComponent.Builder.onHover(init: HoverEventBuilder.() -> HoverEvent<T>): TextComponent.Builder {
    val event = HoverEventBuilder().init()
    return hoverEvent(event)
}

fun TextComponent.Builder.onClick(init: ClickEventBuilder.() -> Unit): TextComponent.Builder {
    val builder = ClickEventBuilder()
    builder.init()
    return clickEvent(builder.build())
}


class ClickEventBuilder internal constructor() {
    lateinit var action: ClickEvent.Action
    lateinit var value: String

    internal fun build() =  ClickEvent.clickEvent(action, value)
}

class HoverEventBuilder internal constructor() {
    fun showText(text: Component) = HoverEvent.hoverEvent(HoverEvent.Action.SHOW_TEXT, text)
    fun showItem(item: HoverEvent.ShowItem) = HoverEvent.hoverEvent(HoverEvent.Action.SHOW_ITEM, item)
    fun showEntity(entity: HoverEvent.ShowEntity) = HoverEvent.hoverEvent(HoverEvent.Action.SHOW_ENTITY, entity)
}
