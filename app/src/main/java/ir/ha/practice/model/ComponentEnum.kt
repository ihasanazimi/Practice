package ir.ha.practice.model

enum class ComponentEnum {
    thread,
    costume_view,
    flow,
    animation,
    broadcast_receiver,
    room,
    fragment,
    material,
    firbase,
    load_images,
    retrofit,
    notification,
    service,
    recyclerview,
    view_pager,
    medial_player,
    video_player,
    exo_player,
    mvvm,
    rx,
    rx_mvvm
}


object Components {
    val sampleComponents = arrayListOf<String>()
    init {
        sampleComponents.add("thread")
        sampleComponents.add("costume_view")
        sampleComponents.add("flow")
        sampleComponents.add("animation")
        sampleComponents.add("broadcast_receiver")
        sampleComponents.add("room")
        sampleComponents.add("fragment")
        sampleComponents.add("material")
        sampleComponents.add("firbase")
        sampleComponents.add("load_images")
        sampleComponents.add("retrofit")
        sampleComponents.add("notification")
        sampleComponents.add("service")
        sampleComponents.add("recyclerview")
        sampleComponents.add("view_pager")
        sampleComponents.add("medial_player")
        sampleComponents.add("video_player")
        sampleComponents.add("exo_player")
        sampleComponents.add("mvvm")
        sampleComponents.add("rx")
        sampleComponents.add("rx_mvvm")
    }
}
