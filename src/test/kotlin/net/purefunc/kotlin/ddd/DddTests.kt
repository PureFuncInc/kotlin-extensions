package net.purefunc.kotlin.ddd

import net.purefunc.kotlin.ddd.infrastructure.enu.Deleted
import net.purefunc.kotlin.ext.toPrettyJson
import net.purefunc.kotlin.ext.unixTimeMilli
import org.junit.jupiter.api.Test

class DddTests {

    @Test
    internal fun `test ddd classed`() {
        println(
            SampleAggregateRoot(
                entityId = SampleId(
                    uuid = unixTimeMilli,
                    createUser = "",
                    lastModifiedUser = "",
                    identity = 1,
                    memo = "memo",
                ),
                key = Key("key"),
                value = "value",
            ).toPrettyJson()
        )

        println(
            SampleEntity(
                entityId = SampleId(
                    uuid = unixTimeMilli,
                    createUser = "",
                    lastModifiedUser = "",
                    identity = 1,
                    memo = "memo",
                ),
                key = Key("key"),
            ).toPrettyJson()
        )

        println(
            SamplePO(
                id = null,
                uuid = unixTimeMilli,
                deleted = Deleted.YES,
                createDate = unixTimeMilli,
                lastModifiedDate = unixTimeMilli,
                createUser = "create user",
                lastModifiedUser = "last modified date",
                identity = 1,
                memo = "memo",
            ).toPrettyJson()
        )
    }
}
