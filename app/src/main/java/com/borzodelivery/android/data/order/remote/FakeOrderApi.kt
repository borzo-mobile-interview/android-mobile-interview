package com.borzodelivery.android.data.order.remote

// In case you have network issues, uncomment this file

//import kotlinx.coroutines.Dispatchers
//import kotlinx.coroutines.delay
//import kotlinx.coroutines.withContext
//
//class FakeOrderApi : OrderApi {
//
//    override suspend fun loadOrders(): OrderListResponse {
//        val sinceId: Long? = null
//        val limit: Int = 10
//
//        return withContext(Dispatchers.IO) {
//            val firstId = sinceId ?: generateRandomLong(1L, 1000L)
//            delay(generateRandomLong(300L, 600L))
//            OrderListResponse((0 until limit).map { idOffset ->
//                generateRandomOrder(firstId + idOffset)
//            })
//        }
//    }
//
//    private fun generateRandomLong(min: Long, max: Long): Long {
//        return (Math.random() * (max - min) + min).toLong()
//    }
//
//    private fun generateRandomOrder(id: Long) = OrderDto(
//        orderId = id.toString(),
//        address = streets.random(),
//        date = dates.random(),
//        text = textSamples.random()
//    )
//
//    companion object {
//
//        private val streets = listOf(
//            "Linking Road",
//            "Colaba Causeway",
//            "Bandra",
//            "Dadabhai Naoroji Road",
//            "Swami Vivekananda Road",
//            "Carter Road Promenade",
//            "Pali Hill",
//            "Carmichael Road",
//            "Marine Drive",
//            "Lamington Road",
//            "Fashion Street",
//            "Fort",
//            "Malabar Hill",
//            "Bellasis Road",
//            "Pandita Ramabai Marg",
//            "Hill Road",
//            "Pedder Road",
//            "Altamount Road",
//            "Churchgate",
//            "Charni Road",
//            "Marine Lines",
//            "Shamaldas Gandhi Marg",
//        )
//
//        private val textSamples = listOf(
//            "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec tellus turpis, tempor nec sagittis in, finibus quis orci. Praesent dignissim consectetur tempor.",
//            "Suspendisse venenatis, libero vitae varius volutpat, justo tortor volutpat libero, ac accumsan sem mi at enim. Morbi eget aliquam tortor.",
//            "Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas. In hac habitasse platea dictumst.",
//            "Duis urna arcu, ullamcorper a ipsum ac, accumsan tempus augue. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia curae; Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos.",
//            "Maecenas vehicula, arcu et dignissim tincidunt, justo justo dignissim enim, nec tincidunt nisi eros nec magna.",
//            "Donec eu efficitur nunc, at vestibulum erat. Nam quis elit convallis, molestie dolor quis, vulputate metus.",
//            "Fusce eu massa id ante pulvinar lacinia.",
//            "Nulla eleifend, enim sed viverra ultrices, turpis mauris tristique sapien, eu interdum massa massa vel eros. Phasellus posuere erat odio, euismod mattis purus sollicitudin in.",
//            "Mauris venenatis sit amet turpis sit amet imperdiet. Pellentesque congue in lacus sit amet placerat.",
//            "Maecenas id risus gravida, rutrum felis nec, feugiat leo. Nam tortor tellus, tempor nec elementum ut, viverra vel arcu. Donec ligula lacus, pretium non viverra sed, sagittis nec sapien.",
//            "Maecenas ac magna est. Mauris sed lacinia metus. Phasellus aliquam ut sapien sed sagittis. Duis mollis neque eget risus imperdiet, porta mattis enim congue.",
//            "Etiam quis felis in est mattis tempus.",
//            "Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos. Nullam ultrices neque eget felis ultricies, et dignissim quam fringilla.",
//            "Vivamus finibus ante felis, ut semper nisl elementum ac. Quisque placerat odio non augue ultrices, eu vestibulum nibh congue. Sed et feugiat erat, ac dapibus dolor.",
//            "Quisque volutpat tellus in nibh fermentum rutrum. Morbi lobortis varius nunc, a venenatis ipsum lacinia ut.",
//            "Aliquam erat volutpat. Nulla facilisi. Ut hendrerit commodo quam. Proin sollicitudin, arcu ut sodales rutrum, tortor nibh iaculis urna, ac ultrices eros erat bibendum ex.",
//            "Nullam semper ultricies augue, eget pretium elit accumsan eu.",
//            "Sed suscipit purus ut ex rutrum faucibus. Ut imperdiet ligula dolor, in luctus arcu vulputate eget. Vestibulum sagittis vitae eros sed dictum.",
//            "Duis quis mauris aliquet, feugiat lacus et, ornare dui. Phasellus eu congue metus, in sodales nulla.",
//            "Ut fermentum, quam id pulvinar condimentum, massa eros pulvinar augue, id aliquam ipsum urna sed purus. Nam ullamcorper laoreet elit ac volutpat.",
//            "Praesent varius pulvinar ex quis sagittis. Nam ultrices, mauris non ultricies eleifend, tellus felis faucibus ligula, quis interdum arcu nisl eu eros.",
//            "Aliquam imperdiet non sem quis feugiat. Fusce non malesuada libero. Mauris dictum, mi non suscipit dapibus, orci justo fringilla orci, et faucibus lectus mauris at mauris.",
//            "Nunc tristique erat et arcu dapibus rhoncus at et sapien. Donec at auctor diam. Nulla varius ex eros, posuere porta enim lobortis dignissim. Curabitur eu gravida orci.",
//            "In mi neque, ornare nec maximus at, accumsan in nulla.",
//            "Ut vel pellentesque nunc. Mauris sollicitudin lacinia cursus.",
//            "Pellentesque ut augue nunc.",
//            "Ut faucibus sodales ligula at volutpat. Vestibulum nec arcu et dolor laoreet hendrerit. Cras nisl turpis, suscipit a luctus et, facilisis vitae metus.",
//            "Aliquam eget laoreet odio. Vivamus id tempus diam. Morbi tempus justo nec dignissim bibendum. Aenean ac sem eget tortor venenatis varius.",
//            "Donec molestie condimentum turpis, sed lobortis libero. Morbi risus ex, sollicitudin id tristique eget, lobortis non felis. Duis id odio magna. Sed ut convallis justo.",
//            "Mauris elementum viverra metus, sit amet venenatis magna pulvinar ut. Donec molestie lectus lacus, placerat sodales arcu pretium sed.",
//            "Nulla in nisl id tortor malesuada semper. Nam congue ultrices urna nec pharetra. Nulla non suscipit arcu. Praesent nec eleifend massa.",
//            "Vestibulum fermentum ut erat sit amet vestibulum. Etiam molestie nisi nunc, sit amet rutrum mauris egestas et. Proin a nisi ac nulla semper euismod ac viverra est.",
//            "Donec rhoncus sapien eu massa tempor finibus. Nunc in lacus tellus. Cras euismod aliquam ante eget tincidunt. Phasellus sollicitudin arcu id enim lacinia tempor.",
//            "Vestibulum sit amet quam vel nisl hendrerit viverra at sit amet risus. Suspendisse mollis posuere gravida. Sed pharetra ante vel quam sollicitudin, varius laoreet urna vehicula.",
//            "Integer tincidunt ultricies consectetur. Pellentesque eu lectus at ex gravida dignissim. Nullam auctor auctor risus, non semper orci elementum vitae.",
//            "Curabitur semper quis dolor volutpat dignissim. Morbi varius felis nec nulla dignissim auctor. Nullam non augue commodo, lacinia mi ut, accumsan orci.",
//            "Praesent vulputate eleifend nunc quis vulputate.",
//            "Pellentesque rutrum feugiat nisl, in faucibus augue ultrices in.",
//            "Sed finibus auctor nunc at ultricies. Etiam lacinia ex vel egestas dictum. Proin mattis tellus dui. Morbi ornare congue scelerisque. Mauris dictum bibendum quam sed faucibus.",
//        )
//
//        private val dates = listOf(
//            "2023-09-19T21:08:36+05:30",
//            "2023-09-19T09:29:22+05:30",
//            "2023-09-19T18:13:38+05:30",
//            "2023-09-19T12:43:27+05:30",
//            "2023-09-19T19:16:37+05:30",
//            "2023-09-19T16:32:42+05:30",
//            "2023-09-19T11:07:21+05:30",
//            "2023-09-19T17:44:02+05:30",
//            "2023-09-19T17:55:43+05:30",
//            "2023-09-19T12:32:21+05:30",
//        )
//
//    }
//
//}