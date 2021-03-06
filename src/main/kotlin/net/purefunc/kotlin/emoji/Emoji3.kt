package net.purefunc.kotlin.emoji

enum class Emoji3(
    private val intArray: IntArray,
) {
    // ๐๏ธ ๐๏ธ ๐๏ธ
    ROLLED_UP_NEWSPAPER(intArrayOf(0x1F5DE, 0xFE0F)),

    // ๐ ๐ ๐
    BOOKMARK_TABS(intArrayOf(0x1F4D1)),

    // ๐ ๐ ๐
    BOOKMARK(intArrayOf(0x1F516)),

    // ๐ท๏ธ ๐ท๏ธ ๐ท๏ธ
    LABEL(intArrayOf(0x1F3F7, 0xFE0F)),

    // ๐ฐ ๐ฐ ๐ฐ
    MONEY_BAG(intArrayOf(0x1F4B0)),

    // ๐ช ๐ช ๐ช
    COIN(intArrayOf(0x1FA99)),

    // ๐ด ๐ด ๐ด
    YEN_BANKNOTE(intArrayOf(0x1F4B4)),

    // ๐ต ๐ต ๐ต
    DOLLAR_BANKNOTE(intArrayOf(0x1F4B5)),

    // ๐ถ ๐ถ ๐ถ
    EURO_BANKNOTE(intArrayOf(0x1F4B6)),

    // ๐ท ๐ท ๐ท
    POUND_BANKNOTE(intArrayOf(0x1F4B7)),

    // ๐ธ ๐ธ ๐ธ
    MONEY_WITH_WINGS(intArrayOf(0x1F4B8)),

    // ๐ณ ๐ณ ๐ณ
    CREDIT_CARD(intArrayOf(0x1F4B3)),

    // ๐งพ ๐งพ ๐งพ
    RECEIPT(intArrayOf(0x1F9FE)),

    // ๐น ๐น ๐น
    CHART_INCREASING_WITH_YEN(intArrayOf(0x1F4B9)),

    // โ๏ธ โ๏ธ โ๏ธ
    ENVELOPE(intArrayOf(0x2709, 0xFE0F)),

    // ๐ง ๐ง ๐ง
    E_MAIL(intArrayOf(0x1F4E7)),

    // ๐จ ๐จ ๐จ
    INCOMING_ENVELOPE(intArrayOf(0x1F4E8)),

    // ๐ฉ ๐ฉ ๐ฉ
    ENVELOPE_WITH_ARROW(intArrayOf(0x1F4E9)),

    // ๐ค ๐ค ๐ค
    OUTBOX_TRAY(intArrayOf(0x1F4E4)),

    // ๐ฅ ๐ฅ ๐ฅ
    INBOX_TRAY(intArrayOf(0x1F4E5)),

    // ๐ฆ ๐ฆ ๐ฆ
    PACKAGES(intArrayOf(0x1F4E6)),

    // ๐ซ ๐ซ ๐ซ
    CLOSED_MAILBOX_WITH_RAISED_FLAG(intArrayOf(0x1F4EB)),

    // ๐ช ๐ช ๐ช
    CLOSED_MAILBOX_WITH_LOWERED_FLAG(intArrayOf(0x1F4EA)),

    // ๐ฌ ๐ฌ ๐ฌ
    OPEN_MAILBOX_WITH_RAISED_FLAG(intArrayOf(0x1F4EC)),

    // ๐ญ ๐ญ ๐ญ
    OPEN_MAILBOX_WITH_LOWERED_FLAG(intArrayOf(0x1F4ED)),

    // ๐ฎ ๐ฎ ๐ฎ
    POSTBOX(intArrayOf(0x1F4EE)),

    // ๐ณ๏ธ ๐ณ๏ธ ๐ณ๏ธ
    BALLOT_BOX_WITH_BALLOT(intArrayOf(0x1F5F3, 0xFE0F)),

    // โ๏ธ โ๏ธ โ๏ธ
    PENCIL(intArrayOf(0x270F, 0xFE0F)),

    // โ๏ธ โ๏ธ โ๏ธ
    BLACK_NIB(intArrayOf(0x2712, 0xFE0F)),

    // ๐๏ธ ๐๏ธ ๐๏ธ
    FOUNTAIN_PEN(intArrayOf(0x1F58B, 0xFE0F)),

    // ๐๏ธ ๐๏ธ ๐๏ธ
    PEN(intArrayOf(0x1F58A, 0xFE0F)),

    // ๐๏ธ ๐๏ธ ๐๏ธ
    PAINTBRUSH(intArrayOf(0x1F58C, 0xFE0F)),

    // ๐๏ธ ๐๏ธ ๐๏ธ
    CRAYON(intArrayOf(0x1F58D, 0xFE0F)),

    // ๐ ๐ ๐
    MEMO(intArrayOf(0x1F4DD)),

    // ๐ผ ๐ผ ๐ผ
    BRIEFCASE(intArrayOf(0x1F4BC)),

    // ๐ ๐ ๐
    FILE_FOLDER(intArrayOf(0x1F4C1)),

    // ๐ ๐ ๐
    OPEN_FILE_FOLDER(intArrayOf(0x1F4C2)),

    // ๐๏ธ ๐๏ธ ๐๏ธ
    CARD_INDEX_DIVIDERS(intArrayOf(0x1F5C2, 0xFE0F)),

    // ๐ ๐ ๐
    CALENDAR(intArrayOf(0x1F4C5)),

    // ๐ ๐ ๐
    TEAR_OFF_CALENDAR(intArrayOf(0x1F4C6)),

    // ๐๏ธ ๐๏ธ ๐๏ธ
    SPIRAL_NOTEPAD(intArrayOf(0x1F5D2, 0xFE0F)),

    // ๐๏ธ ๐๏ธ ๐๏ธ
    SPIRAL_CALENDAR(intArrayOf(0x1F5D3, 0xFE0F)),

    // ๐ ๐ ๐
    CARD_INDEX(intArrayOf(0x1F4C7)),

    // ๐ ๐ ๐
    CHART_INCREASING(intArrayOf(0x1F4C8)),

    // ๐ ๐ ๐
    CHART_DECREASING(intArrayOf(0x1F4C9)),

    // ๐ ๐ ๐
    BAR_CHART(intArrayOf(0x1F4CA)),

    // ๐ ๐ ๐
    CLIPBOARD(intArrayOf(0x1F4CB)),

    // ๐ ๐ ๐
    PUSHPIN(intArrayOf(0x1F4CC)),

    // ๐ ๐ ๐
    ROUND_PUSHPIN(intArrayOf(0x1F4CD)),

    // ๐ ๐ ๐
    PAPERCLIP(intArrayOf(0x1F4CE)),

    // ๐๏ธ ๐๏ธ ๐๏ธ
    LINKED_PAPERCLIPS(intArrayOf(0x1F587, 0xFE0F)),

    // ๐ ๐ ๐
    STRAIGHT_RULER(intArrayOf(0x1F4CF)),

    // ๐ ๐ ๐
    TRIANGULAR_RULER(intArrayOf(0x1F4D0)),

    // โ๏ธ โ๏ธ โ๏ธ
    SCISSORS(intArrayOf(0x2702, 0xFE0F)),

    // ๐๏ธ ๐๏ธ ๐๏ธ
    CARD_FILE_BOX(intArrayOf(0x1F5C3, 0xFE0F)),

    // ๐๏ธ ๐๏ธ ๐๏ธ
    FILE_CABINET(intArrayOf(0x1F5C4, 0xFE0F)),

    // ๐๏ธ ๐๏ธ ๐๏ธ
    WASTEBASKET(intArrayOf(0x1F5D1, 0xFE0F)),

    // ๐ ๐ ๐
    LOCKED(intArrayOf(0x1F512)),

    // ๐ ๐ ๐
    UNLOCKED(intArrayOf(0x1F513)),

    // ๐ ๐ ๐
    LOCKED_WITH_PEN(intArrayOf(0x1F50F)),

    // ๐ ๐ ๐
    LOCKED_WITH_KEY(intArrayOf(0x1F510)),

    // ๐ ๐ ๐
    KEY(intArrayOf(0x1F511)),

    // ๐๏ธ ๐๏ธ ๐๏ธ
    OLD_KEY(intArrayOf(0x1F5DD, 0xFE0F)),

    // ๐จ ๐จ ๐จ
    HAMMER(intArrayOf(0x1F528)),

    // ๐ช ๐ช ๐ช
    AXE(intArrayOf(0x1FA93)),

    // โ๏ธ โ๏ธ โ๏ธ
    PICK(intArrayOf(0x26CF, 0xFE0F)),

    // โ๏ธ โ๏ธ โ๏ธ
    HAMMER_AND_PICK(intArrayOf(0x2692, 0xFE0F)),

    // ๐?๏ธ ๐?๏ธ ๐?๏ธ
    HAMMER_AND_WRENCH(intArrayOf(0x1F6E0, 0xFE0F)),

    // ๐ก๏ธ ๐ก๏ธ ๐ก๏ธ
    DAGGER(intArrayOf(0x1F5E1, 0xFE0F)),

    // โ๏ธ โ๏ธ โ๏ธ
    CROSSED_SWORDS(intArrayOf(0x2694, 0xFE0F)),

    // ๐ซ ๐ซ ๐ซ
    WATER_PISTOL(intArrayOf(0x1F52B)),

    // ๐ช ๐ช ๐ช
    BOOMERANG(intArrayOf(0x1FA83)),

    // ๐น ๐น ๐น
    BOW_AND_ARROW(intArrayOf(0x1F3F9)),

    // ๐ก๏ธ ๐ก๏ธ ๐ก๏ธ
    SHIELD(intArrayOf(0x1F6E1, 0xFE0F)),

    // ๐ช ๐ช ๐ช
    CARPENTRY_SAW(intArrayOf(0x1FA9A)),

    // ๐ง ๐ง ๐ง
    WRENCH(intArrayOf(0x1F527)),

    // ๐ช ๐ช ๐ช
    SCREWDRIVER(intArrayOf(0x1FA9B)),

    // ๐ฉ ๐ฉ ๐ฉ
    NUT_AND_BOLT(intArrayOf(0x1F529)),

    // โ๏ธ โ๏ธ โ๏ธ
    GEAR(intArrayOf(0x2699, 0xFE0F)),

    // ๐๏ธ ๐๏ธ ๐๏ธ
    CLAMP(intArrayOf(0x1F5DC, 0xFE0F)),

    // โ๏ธ โ๏ธ โ๏ธ
    BALANCE_SCALE(intArrayOf(0x2696, 0xFE0F)),

    // ๐ฆฏ ๐ฆฏ ๐ฆฏ
    WHITE_CANE(intArrayOf(0x1F9AF)),

    // ๐ ๐ ๐
    LINK(intArrayOf(0x1F517)),

    // โ๏ธ โ๏ธ โ๏ธ
    CHAINS(intArrayOf(0x26D3, 0xFE0F)),

    // ๐ช ๐ช ๐ช
    HOOK(intArrayOf(0x1FA9D)),

    // ๐งฐ ๐งฐ ๐งฐ
    TOOLBOX(intArrayOf(0x1F9F0)),

    // ๐งฒ ๐งฒ ๐งฒ
    MAGNET(intArrayOf(0x1F9F2)),

    // ๐ช ๐ช ๐ช
    LADDER(intArrayOf(0x1FA9C)),

    // โ๏ธ โ๏ธ โ๏ธ
    ALEMBIC(intArrayOf(0x2697, 0xFE0F)),

    // ๐งช ๐งช ๐งช
    TEST_TUBE(intArrayOf(0x1F9EA)),

    // ๐งซ ๐งซ ๐งซ
    PETRI_DISH(intArrayOf(0x1F9EB)),

    // ๐งฌ ๐งฌ ๐งฌ
    DNA(intArrayOf(0x1F9EC)),

    // ๐ฌ ๐ฌ ๐ฌ
    MICROSCOPE(intArrayOf(0x1F52C)),

    // ๐ญ ๐ญ ๐ญ
    TELESCOPE(intArrayOf(0x1F52D)),

    // ๐ก ๐ก ๐ก
    SATELLITE_ANTENNA(intArrayOf(0x1F4E1)),

    // ๐ ๐ ๐
    SYRINGE(intArrayOf(0x1F489)),

    // ๐ฉธ ๐ฉธ ๐ฉธ
    DROP_OF_BLOOD(intArrayOf(0x1FA78)),

    // ๐ ๐ ๐
    PILL(intArrayOf(0x1F48A)),

    // ๐ฉน ๐ฉน ๐ฉน
    ADHESIVE_BANDAGE(intArrayOf(0x1FA79)),

    // ๐ฉผ ๐ฉผ ๐ฉผ
    CRUTCH(intArrayOf(0x1FA7C)),

    // ๐ฉบ ๐ฉบ ๐ฉบ
    STETHOSCOPE(intArrayOf(0x1FA7A)),

    // ๐ฉป ๐ฉป ๐ฉป
    X_RAY(intArrayOf(0x1FA7B)),

    // ๐ช ๐ช ๐ช
    DOOR(intArrayOf(0x1F6AA)),

    // ๐ ๐ ๐
    ELEVATOR(intArrayOf(0x1F6D7)),

    // ๐ช ๐ช ๐ช
    MIRROR(intArrayOf(0x1FA9E)),

    // ๐ช ๐ช ๐ช
    WINDOW(intArrayOf(0x1FA9F)),

    // ๐๏ธ ๐๏ธ ๐๏ธ
    BED(intArrayOf(0x1F6CF, 0xFE0F)),

    // ๐๏ธ ๐๏ธ ๐๏ธ
    COUCH_AND_LAMP(intArrayOf(0x1F6CB, 0xFE0F)),

    // ๐ช ๐ช ๐ช
    CHAIR(intArrayOf(0x1FA91)),

    // ๐ฝ ๐ฝ ๐ฝ
    TOILET(intArrayOf(0x1F6BD)),

    // ๐ช? ๐ช? ๐ช?
    PLUNGER(intArrayOf(0x1FAA0)),

    // ๐ฟ ๐ฟ ๐ฟ
    SHOWER(intArrayOf(0x1F6BF)),

    // ๐ ๐ ๐
    BATHTUB(intArrayOf(0x1F6C1)),

    // ๐ชค ๐ชค ๐ชค
    MOUSE_TRAP(intArrayOf(0x1FAA4)),

    // ๐ช ๐ช ๐ช
    RAZOR(intArrayOf(0x1FA92)),

    // ๐งด ๐งด ๐งด
    LOTION_BOTTLE(intArrayOf(0x1F9F4)),

    // ๐งท ๐งท ๐งท
    SAFETY_PIN(intArrayOf(0x1F9F7)),

    // ๐งน ๐งน ๐งน
    BROOM(intArrayOf(0x1F9F9)),

    // ๐งบ ๐งบ ๐งบ
    BASKET(intArrayOf(0x1F9FA)),

    // ๐งป ๐งป ๐งป
    ROLL_OF_PAPER(intArrayOf(0x1F9FB)),

    // ๐ชฃ ๐ชฃ ๐ชฃ
    BUCKET(intArrayOf(0x1FAA3)),

    // ๐งผ ๐งผ ๐งผ
    SOAP(intArrayOf(0x1F9FC)),

    // ๐ซง ๐ซง ๐ซง
    BUBBLES(intArrayOf(0x1FAE7)),

    // ๐ชฅ ๐ชฅ ๐ชฅ
    TOOTHBRUSH(intArrayOf(0x1FAA5)),

    // ๐งฝ ๐งฝ ๐งฝ
    SPONGE(intArrayOf(0x1F9FD)),

    // ๐งฏ ๐งฏ ๐งฏ
    FIRE_EXTINGUISHER(intArrayOf(0x1F9EF)),

    // ๐ ๐ ๐
    SHOPPING_CART(intArrayOf(0x1F6D2)),

    // ๐ฌ ๐ฌ ๐ฌ
    CIGARETTE(intArrayOf(0x1F6AC)),

    // โฐ๏ธ โฐ๏ธ โฐ๏ธ
    COFFIN(intArrayOf(0x26B0, 0xFE0F)),

    // ๐ชฆ ๐ชฆ ๐ชฆ
    HEADSTONE(intArrayOf(0x1FAA6)),

    // โฑ๏ธ โฑ๏ธ โฑ๏ธ
    FUNERAL_URN(intArrayOf(0x26B1, 0xFE0F)),

    // ๐ฟ ๐ฟ ๐ฟ
    MOAI(intArrayOf(0x1F5FF)),

    // ๐ชง ๐ชง ๐ชง
    PLACARD(intArrayOf(0x1FAA7)),

    // ๐ชช ๐ชช ๐ชช
    IDENTIFICATION_CARD(intArrayOf(0x1FAAA)),

    // ๐ง ๐ง ๐ง
    ATM_SIGN(intArrayOf(0x1F3E7)),

    // ๐ฎ ๐ฎ ๐ฎ
    LITTER_IN_BIN_SIGN(intArrayOf(0x1F6AE)),

    // ๐ฐ ๐ฐ ๐ฐ
    POTABLE_WATER(intArrayOf(0x1F6B0)),

    // โฟ โฟ โฟ
    WHEELCHAIR_SYMBOL(intArrayOf(0x267F)),

    // ๐น ๐น ๐น
    MENS_ROOM(intArrayOf(0x1F6B9)),

    // ๐บ ๐บ ๐บ
    WOMENS_ROOM(intArrayOf(0x1F6BA)),

    // ๐ป ๐ป ๐ป
    RESTROOM(intArrayOf(0x1F6BB)),

    // ๐ผ ๐ผ ๐ผ
    BABY_SYMBOL(intArrayOf(0x1F6BC)),

    // ๐พ ๐พ ๐พ
    WATER_CLOSET(intArrayOf(0x1F6BE)),

    // ๐ ๐ ๐
    PASSPORT_CONTROL(intArrayOf(0x1F6C2)),

    // ๐ ๐ ๐
    CUSTOMS(intArrayOf(0x1F6C3)),

    // ๐ ๐ ๐
    BAGGAGE_CLAIM(intArrayOf(0x1F6C4)),

    // ๐ ๐ ๐
    LEFT_LUGGAGE(intArrayOf(0x1F6C5)),

    // โ?๏ธ โ?๏ธ โ?๏ธ
    WARNING(intArrayOf(0x26A0, 0xFE0F)),

    // ๐ธ ๐ธ ๐ธ
    CHILDREN_CROSSING(intArrayOf(0x1F6B8)),

    // โ โ โ
    NO_ENTRY(intArrayOf(0x26D4)),

    // ๐ซ ๐ซ ๐ซ
    PROHIBITED(intArrayOf(0x1F6AB)),

    // ๐ณ ๐ณ ๐ณ
    NO_BICYCLES(intArrayOf(0x1F6B3)),

    // ๐ญ ๐ญ ๐ญ
    NO_SMOKING(intArrayOf(0x1F6AD)),

    // ๐ฏ ๐ฏ ๐ฏ
    NO_LITTERING(intArrayOf(0x1F6AF)),

    // ๐ฑ ๐ฑ ๐ฑ
    NON_POTABLE_WATER(intArrayOf(0x1F6B1)),

    // ๐ท ๐ท ๐ท
    NO_PEDESTRIANS(intArrayOf(0x1F6B7)),

    // ๐ต ๐ต ๐ต
    NO_MOBILE_PHONES(intArrayOf(0x1F4F5)),

    // ๐ ๐ ๐
    NO_ONE_UNDER_EIGHTEEN(intArrayOf(0x1F51E)),

    // โข๏ธ โข๏ธ โข๏ธ
    RADIOACTIVE(intArrayOf(0x2622, 0xFE0F)),

    // โฃ๏ธ โฃ๏ธ โฃ๏ธ
    BIOHAZARD(intArrayOf(0x2623, 0xFE0F)),

    // โฌ๏ธ โฌ๏ธ โฌ๏ธ
    UP_ARROW(intArrayOf(0x2B06, 0xFE0F)),

    // โ๏ธ โ๏ธ โ๏ธ
    UP_RIGHT_ARROW(intArrayOf(0x2197, 0xFE0F)),

    // โก๏ธ โก๏ธ โก๏ธ
    RIGHT_ARROW(intArrayOf(0x27A1, 0xFE0F)),

    // โ๏ธ โ๏ธ โ๏ธ
    DOWN_RIGHT_ARROW(intArrayOf(0x2198, 0xFE0F)),

    // โฌ๏ธ โฌ๏ธ โฌ๏ธ
    DOWN_ARROW(intArrayOf(0x2B07, 0xFE0F)),

    // โ๏ธ โ๏ธ โ๏ธ
    DOWN_LEFT_ARROW(intArrayOf(0x2199, 0xFE0F)),

    // โฌ๏ธ โฌ๏ธ โฌ๏ธ
    LEFT_ARROW(intArrayOf(0x2B05, 0xFE0F)),

    // โ๏ธ โ๏ธ โ๏ธ
    UP_LEFT_ARROW(intArrayOf(0x2196, 0xFE0F)),

    // โ๏ธ โ๏ธ โ๏ธ
    UP_DOWN_ARROW(intArrayOf(0x2195, 0xFE0F)),

    // โ๏ธ โ๏ธ โ๏ธ
    LEFT_RIGHT_ARROW(intArrayOf(0x2194, 0xFE0F)),

    // โฉ๏ธ โฉ๏ธ โฉ๏ธ
    RIGHT_ARROW_CURVING_LEFT(intArrayOf(0x21A9, 0xFE0F)),

    // โช๏ธ โช๏ธ โช๏ธ
    LEFT_ARROW_CURVING_RIGHT(intArrayOf(0x21AA, 0xFE0F)),

    // โคด๏ธ โคด๏ธ โคด๏ธ
    RIGHT_ARROW_CURVING_UP(intArrayOf(0x2934, 0xFE0F)),

    // โคต๏ธ โคต๏ธ โคต๏ธ
    RIGHT_ARROW_CURVING_DOWN(intArrayOf(0x2935, 0xFE0F)),

    // ๐ ๐ ๐
    CLOCKWISE_VERTICAL_ARROWS(intArrayOf(0x1F503)),

    // ๐ ๐ ๐
    COUNTERCLOCKWISE_ARROWS_BUTTON(intArrayOf(0x1F504)),

    // ๐ ๐ ๐
    BACK_ARROW(intArrayOf(0x1F519)),

    // ๐ ๐ ๐
    END_ARROW(intArrayOf(0x1F51A)),

    // ๐ ๐ ๐
    ON_ARROW(intArrayOf(0x1F51B)),

    // ๐ ๐ ๐
    SOON_ARROW(intArrayOf(0x1F51C)),

    // ๐ ๐ ๐
    TOP_ARROW(intArrayOf(0x1F51D)),

    // ๐ ๐ ๐
    PLACE_OF_WORSHIP(intArrayOf(0x1F6D0)),

    // โ๏ธ โ๏ธ โ๏ธ
    ATOM_SYMBOL(intArrayOf(0x269B, 0xFE0F)),

    // ๐๏ธ ๐๏ธ ๐๏ธ
    OM(intArrayOf(0x1F549, 0xFE0F)),

    // โก๏ธ โก๏ธ โก๏ธ
    STAR_OF_DAVID(intArrayOf(0x2721, 0xFE0F)),

    // โธ๏ธ โธ๏ธ โธ๏ธ
    WHEEL_OF_DHARMA(intArrayOf(0x2638, 0xFE0F)),

    // โฏ๏ธ โฏ๏ธ โฏ๏ธ
    YIN_YANG(intArrayOf(0x262F, 0xFE0F)),

    // โ๏ธ โ๏ธ โ๏ธ
    LATIN_CROSS(intArrayOf(0x271D, 0xFE0F)),

    // โฆ๏ธ โฆ๏ธ โฆ๏ธ
    ORTHODOX_CROSS(intArrayOf(0x2626, 0xFE0F)),

    // โช๏ธ โช๏ธ โช๏ธ
    STAR_AND_CRESCENT(intArrayOf(0x262A, 0xFE0F)),

    // โฎ๏ธ โฎ๏ธ โฎ๏ธ
    PEACE_SYMBOL(intArrayOf(0x262E, 0xFE0F)),

    // ๐ ๐ ๐
    MENORAH(intArrayOf(0x1F54E)),

    // ๐ฏ ๐ฏ ๐ฏ
    DOTTED_SIX_POINTED_STAR(intArrayOf(0x1F52F)),

    // โ โ โ
    ARIES(intArrayOf(0x2648)),

    // โ โ โ
    TAURUS(intArrayOf(0x2649)),

    // โ โ โ
    GEMINI(intArrayOf(0x264A)),

    // โ โ โ
    CANCER(intArrayOf(0x264B)),

    // โ โ โ
    LEO(intArrayOf(0x264C)),

    // โ โ โ
    VIRGO(intArrayOf(0x264D)),

    // โ โ โ
    LIBRA(intArrayOf(0x264E)),

    // โ โ โ
    SCORPIO(intArrayOf(0x264F)),

    // โ โ โ
    SAGITTARIUS(intArrayOf(0x2650)),

    // โ โ โ
    CAPRICORN(intArrayOf(0x2651)),

    // โ โ โ
    AQUARIUS(intArrayOf(0x2652)),

    // โ โ โ
    PISCES(intArrayOf(0x2653)),

    // โ โ โ
    OPHIUCHUS(intArrayOf(0x26CE)),

    // ๐ ๐ ๐
    SHUFFLE_TRACKS_BUTTON(intArrayOf(0x1F500)),

    // ๐ ๐ ๐
    REPEAT_BUTTON(intArrayOf(0x1F501)),

    // ๐ ๐ ๐
    REPEAT_SINGLE_BUTTON(intArrayOf(0x1F502)),

    // โถ๏ธ โถ๏ธ โถ๏ธ
    PLAY_BUTTON(intArrayOf(0x25B6, 0xFE0F)),

    // โฉ โฉ โฉ
    FAST_FORWARD_BUTTON(intArrayOf(0x23E9)),

    // โญ๏ธ โญ๏ธ โญ๏ธ
    NEXT_TRACK_BUTTON(intArrayOf(0x23ED, 0xFE0F)),

    // โฏ๏ธ โฏ๏ธ โฏ๏ธ
    PLAY_OR_PAUSE_BUTTON(intArrayOf(0x23EF, 0xFE0F)),

    // โ๏ธ โ๏ธ โ๏ธ
    REVERSE_BUTTON(intArrayOf(0x25C0, 0xFE0F)),

    // โช โช โช
    FAST_REVERSE_BUTTON(intArrayOf(0x23EA)),

    // โฎ๏ธ โฎ๏ธ โฎ๏ธ
    LAST_TRACK_BUTTON(intArrayOf(0x23EE, 0xFE0F)),

    // ๐ผ ๐ผ ๐ผ
    UPWARDS_BUTTON(intArrayOf(0x1F53C)),

    // โซ โซ โซ
    FAST_UP_BUTTON(intArrayOf(0x23EB)),

    // ๐ฝ ๐ฝ ๐ฝ
    DOWNWARDS_BUTTON(intArrayOf(0x1F53D)),

    // โฌ โฌ โฌ
    FAST_DOWN_BUTTON(intArrayOf(0x23EC)),

    // โธ๏ธ โธ๏ธ โธ๏ธ
    PAUSE_BUTTON(intArrayOf(0x23F8, 0xFE0F)),

    // โน๏ธ โน๏ธ โน๏ธ
    STOP_BUTTON(intArrayOf(0x23F9, 0xFE0F)),

    // โบ๏ธ โบ๏ธ โบ๏ธ
    RECORD_BUTTON(intArrayOf(0x23FA, 0xFE0F)),

    // โ๏ธ โ๏ธ โ๏ธ
    EJECT_BUTTON(intArrayOf(0x23CF, 0xFE0F)),

    // ๐ฆ ๐ฆ ๐ฆ
    CINEMA(intArrayOf(0x1F3A6)),

    // ๐ ๐ ๐
    DIM_BUTTON(intArrayOf(0x1F505)),

    // ๐ ๐ ๐
    BRIGHT_BUTTON(intArrayOf(0x1F506)),

    // ๐ถ ๐ถ ๐ถ
    ANTENNA_BARS(intArrayOf(0x1F4F6)),

    // ๐ณ ๐ณ ๐ณ
    VIBRATION_MODE(intArrayOf(0x1F4F3)),

    // ๐ด ๐ด ๐ด
    MOBILE_PHONE_OFF(intArrayOf(0x1F4F4)),

    // โ๏ธ โ๏ธ โ๏ธ
    FEMALE_SIGN(intArrayOf(0x2640, 0xFE0F)),

    // โ๏ธ โ๏ธ โ๏ธ
    MALE_SIGN(intArrayOf(0x2642, 0xFE0F)),

    // โง๏ธ โง๏ธ โง๏ธ
    TRANSGENDER_SYMBOL(intArrayOf(0x26A7, 0xFE0F)),

    // โ๏ธ โ๏ธ โ๏ธ
    MULTIPLY(intArrayOf(0x2716, 0xFE0F)),

    // โ โ โ
    PLUS(intArrayOf(0x2795)),

    // โ โ โ
    MINUS(intArrayOf(0x2796)),

    // โ โ โ
    DIVIDE(intArrayOf(0x2797)),

    // ๐ฐ ๐ฐ ๐ฐ
    HEAVY_EQUALS_SIGN(intArrayOf(0x1F7F0)),

    // โพ๏ธ โพ๏ธ โพ๏ธ
    INFINITY(intArrayOf(0x267E, 0xFE0F)),

    // โผ๏ธ โผ๏ธ โผ๏ธ
    DOUBLE_EXCLAMATION_MARK(intArrayOf(0x203C, 0xFE0F)),

    // โ๏ธ โ๏ธ โ๏ธ
    EXCLAMATION_QUESTION_MARK(intArrayOf(0x2049, 0xFE0F)),

    // โ โ โ
    RED_QUESTION_MARK(intArrayOf(0x2753)),

    // โ โ โ
    WHITE_QUESTION_MARK(intArrayOf(0x2754)),

    // โ โ โ
    WHITE_EXCLAMATION_MARK(intArrayOf(0x2755)),

    // โ โ โ
    RED_EXCLAMATION_MARK(intArrayOf(0x2757)),

    // ใฐ๏ธ ใฐ๏ธ ใฐ๏ธ
    WAVY_DASH(intArrayOf(0x3030, 0xFE0F)),

    // ๐ฑ ๐ฑ ๐ฑ
    CURRENCY_EXCHANGE(intArrayOf(0x1F4B1)),

    // ๐ฒ ๐ฒ ๐ฒ
    HEAVY_DOLLAR_SIGN(intArrayOf(0x1F4B2)),

    // โ๏ธ โ๏ธ โ๏ธ
    MEDICAL_SYMBOL(intArrayOf(0x2695, 0xFE0F)),

    // โป๏ธ โป๏ธ โป๏ธ
    RECYCLING_SYMBOL(intArrayOf(0x267B, 0xFE0F)),

    // โ๏ธ โ๏ธ โ๏ธ
    FLEUR_DE_LIS(intArrayOf(0x269C, 0xFE0F)),

    // ๐ฑ ๐ฑ ๐ฑ
    TRIDENT_EMBLEM(intArrayOf(0x1F531)),

    // ๐ ๐ ๐
    NAME_BADGE(intArrayOf(0x1F4DB)),

    // ๐ฐ ๐ฐ ๐ฐ
    JAPANESE_SYMBOL_FOR_BEGINNER(intArrayOf(0x1F530)),

    // โญ โญ โญ
    HOLLOW_RED_CIRCLE(intArrayOf(0x2B55)),

    // โ โ โ
    CHECK_MARK_BUTTON(intArrayOf(0x2705)),

    // โ๏ธ โ๏ธ โ๏ธ
    CHECK_BOX_WITH_CHECK(intArrayOf(0x2611, 0xFE0F)),

    // โ๏ธ โ๏ธ โ๏ธ
    CHECK_MARK(intArrayOf(0x2714, 0xFE0F)),

    // โ โ โ
    CROSS_MARK(intArrayOf(0x274C)),

    // โ โ โ
    CROSS_MARK_BUTTON(intArrayOf(0x274E)),

    // โฐ โฐ โฐ
    CURLY_LOOP(intArrayOf(0x27B0)),

    // โฟ โฟ โฟ
    DOUBLE_CURLY_LOOP(intArrayOf(0x27BF)),

    // ใฝ๏ธ ใฝ๏ธ ใฝ๏ธ
    PART_ALTERNATION_MARK(intArrayOf(0x303D, 0xFE0F)),

    // โณ๏ธ โณ๏ธ โณ๏ธ
    EIGHT_SPOKED_ASTERISK(intArrayOf(0x2733, 0xFE0F)),

    // โด๏ธ โด๏ธ โด๏ธ
    EIGHT_POINTED_STAR(intArrayOf(0x2734, 0xFE0F)),

    // โ๏ธ โ๏ธ โ๏ธ
    SPARKLE(intArrayOf(0x2747, 0xFE0F)),

    // ยฉ๏ธ ยฉ๏ธ ยฉ๏ธ
    COPYRIGHT(intArrayOf(0x00A9, 0xFE0F)),

    // ยฎ๏ธ ยฎ๏ธ ยฎ๏ธ
    REGISTERED(intArrayOf(0x00AE, 0xFE0F)),

    // โข๏ธ โข๏ธ โข๏ธ
    TRADE_MARK(intArrayOf(0x2122, 0xFE0F)),

    // #๏ธโฃ #๏ธโฃ #๏ธโฃ
    KEYCAP_HASH(intArrayOf(0x0023, 0xFE0F, 0x20E3)),

    // *๏ธโฃ *๏ธโฃ *๏ธโฃ
    KEYCAP_STAR(intArrayOf(0x002A, 0xFE0F, 0x20E3)),

    // 0๏ธโฃ 0๏ธโฃ 0๏ธโฃ
    KEYCAP_0(intArrayOf(0x0030, 0xFE0F, 0x20E3)),

    // 1๏ธโฃ 1๏ธโฃ 1๏ธโฃ
    KEYCAP_1(intArrayOf(0x0031, 0xFE0F, 0x20E3)),

    // 2๏ธโฃ 2๏ธโฃ 2๏ธโฃ
    KEYCAP_2(intArrayOf(0x0032, 0xFE0F, 0x20E3)),

    // 3๏ธโฃ 3๏ธโฃ 3๏ธโฃ
    KEYCAP_3(intArrayOf(0x0033, 0xFE0F, 0x20E3)),

    // 4๏ธโฃ 4๏ธโฃ 4๏ธโฃ
    KEYCAP_4(intArrayOf(0x0034, 0xFE0F, 0x20E3)),

    // 5๏ธโฃ 5๏ธโฃ 5๏ธโฃ
    KEYCAP_5(intArrayOf(0x0035, 0xFE0F, 0x20E3)),

    // 6๏ธโฃ 6๏ธโฃ 6๏ธโฃ
    KEYCAP_6(intArrayOf(0x0036, 0xFE0F, 0x20E3)),

    // 7๏ธโฃ 7๏ธโฃ 7๏ธโฃ
    KEYCAP_7(intArrayOf(0x0037, 0xFE0F, 0x20E3)),

    // 8๏ธโฃ 8๏ธโฃ 8๏ธโฃ
    KEYCAP_8(intArrayOf(0x0038, 0xFE0F, 0x20E3)),

    // 9๏ธโฃ 9๏ธโฃ 9๏ธโฃ
    KEYCAP_9(intArrayOf(0x0039, 0xFE0F, 0x20E3)),

    // ๐ ๐ ๐
    KEYCAP_10(intArrayOf(0x1F51F)),

    // ๐? ๐? ๐?
    INPUT_LATIN_UPPERCASE(intArrayOf(0x1F520)),

    // ๐ก ๐ก ๐ก
    INPUT_LATIN_LOWERCASE(intArrayOf(0x1F521)),

    // ๐ข ๐ข ๐ข
    INPUT_NUMBERS(intArrayOf(0x1F522)),

    // ๐ฃ ๐ฃ ๐ฃ
    INPUT_SYMBOLS(intArrayOf(0x1F523)),

    // ๐ค ๐ค ๐ค
    INPUT_LATIN_LETTERS(intArrayOf(0x1F524)),

    // ๐ฐ๏ธ ๐ฐ๏ธ ๐ฐ๏ธ
    A_BUTTON_BLOOD_TYPE(intArrayOf(0x1F170, 0xFE0F)),

    // ๐ ๐ ๐
    AB_BUTTON_BLOOD_TYPE(intArrayOf(0x1F18E)),

    // ๐ฑ๏ธ ๐ฑ๏ธ ๐ฑ๏ธ
    B_BUTTON_BLOOD_TYPE(intArrayOf(0x1F171, 0xFE0F)),

    // ๐ ๐ ๐
    CL_BUTTON(intArrayOf(0x1F191)),

    // ๐ ๐ ๐
    COOL_BUTTON(intArrayOf(0x1F192)),

    // ๐ ๐ ๐
    FREE_BUTTON(intArrayOf(0x1F193)),

    // โน๏ธ โน๏ธ โน๏ธ
    INFORMATION(intArrayOf(0x2139, 0xFE0F)),

    // ๐ ๐ ๐
    ID_BUTTON(intArrayOf(0x1F194)),

    // โ๏ธ โ๏ธ โ๏ธ
    CIRCLED_M(intArrayOf(0x24C2, 0xFE0F)),

    // ๐ ๐ ๐
    NEW_BUTTON(intArrayOf(0x1F195)),

    // ๐ ๐ ๐
    NG_BUTTON(intArrayOf(0x1F196)),

    // ๐พ๏ธ ๐พ๏ธ ๐พ๏ธ
    O_BUTTON_BLOOD_TYPE(intArrayOf(0x1F17E, 0xFE0F)),

    // ๐ ๐ ๐
    OK_BUTTON(intArrayOf(0x1F197)),

    // ๐ฟ๏ธ ๐ฟ๏ธ ๐ฟ๏ธ
    P_BUTTON(intArrayOf(0x1F17F, 0xFE0F)),

    // ๐ ๐ ๐
    SOS_BUTTON(intArrayOf(0x1F198)),

    // ๐ ๐ ๐
    UP_BUTTON(intArrayOf(0x1F199)),

    // ๐ ๐ ๐
    VS_BUTTON(intArrayOf(0x1F19A)),

    // ๐ ๐ ๐
    JAPANESE_HERE_BUTTON(intArrayOf(0x1F201)),

    // ๐๏ธ ๐๏ธ ๐๏ธ
    JAPANESE_SERVICE_CHARGE_BUTTON(intArrayOf(0x1F202, 0xFE0F)),

    // ๐ท๏ธ ๐ท๏ธ ๐ท๏ธ
    JAPANESE_MONTHLY_AMOUNT_BUTTON(intArrayOf(0x1F237, 0xFE0F)),

    // ๐ถ ๐ถ ๐ถ
    JAPANESE_NOT_FREE_OF_CHARGE_BUTTON(intArrayOf(0x1F236)),

    // ๐ฏ ๐ฏ ๐ฏ
    JAPANESE_RESERVED_BUTTON(intArrayOf(0x1F22F)),

    // ๐ ๐ ๐
    JAPANESE_BARGAIN_BUTTON(intArrayOf(0x1F250)),

    // ๐น ๐น ๐น
    JAPANESE_DISCOUNT_BUTTON(intArrayOf(0x1F239)),

    // ๐ ๐ ๐
    JAPANESE_FREE_OF_CHARGE_BUTTON(intArrayOf(0x1F21A)),

    // ๐ฒ ๐ฒ ๐ฒ
    JAPANESE_PROHIBITED_BUTTON(intArrayOf(0x1F232)),

    // ๐ ๐ ๐
    JAPANESE_ACCEPTABLE_BUTTON(intArrayOf(0x1F251)),

    // ๐ธ ๐ธ ๐ธ
    JAPANESE_APPLICATION_BUTTON(intArrayOf(0x1F238)),

    // ๐ด ๐ด ๐ด
    JAPANESE_PASSING_GRADE_BUTTON(intArrayOf(0x1F234)),

    // ๐ณ ๐ณ ๐ณ
    JAPANESE_VACANCY_BUTTON(intArrayOf(0x1F233)),

    // ใ๏ธ ใ๏ธ ใ๏ธ
    JAPANESE_CONGRATULATIONS_BUTTON(intArrayOf(0x3297, 0xFE0F)),

    // ใ๏ธ ใ๏ธ ใ๏ธ
    JAPANESE_SECRET_BUTTON(intArrayOf(0x3299, 0xFE0F)),

    // ๐บ ๐บ ๐บ
    JAPANESE_OPEN_FOR_BUSINESS_BUTTON(intArrayOf(0x1F23A)),

    // ๐ต ๐ต ๐ต
    JAPANESE_NO_VACANCY_BUTTON(intArrayOf(0x1F235)),

    // ๐ด ๐ด ๐ด
    RED_CIRCLE(intArrayOf(0x1F534)),

    // ๐? ๐? ๐?
    ORANGE_CIRCLE(intArrayOf(0x1F7E0)),

    // ๐ก ๐ก ๐ก
    YELLOW_CIRCLE(intArrayOf(0x1F7E1)),

    // ๐ข ๐ข ๐ข
    GREEN_CIRCLE(intArrayOf(0x1F7E2)),

    // ๐ต ๐ต ๐ต
    BLUE_CIRCLE(intArrayOf(0x1F535)),

    // ๐ฃ ๐ฃ ๐ฃ
    PURPLE_CIRCLE(intArrayOf(0x1F7E3)),

    // ๐ค ๐ค ๐ค
    BROWN_CIRCLE(intArrayOf(0x1F7E4)),

    // โซ โซ โซ
    BLACK_CIRCLE(intArrayOf(0x26AB)),

    // โช โช โช
    WHITE_CIRCLE(intArrayOf(0x26AA)),

    // ๐ฅ ๐ฅ ๐ฅ
    RED_SQUARE(intArrayOf(0x1F7E5)),

    // ๐ง ๐ง ๐ง
    ORANGE_SQUARE(intArrayOf(0x1F7E7)),

    // ๐จ ๐จ ๐จ
    YELLOW_SQUARE(intArrayOf(0x1F7E8)),

    // ๐ฉ ๐ฉ ๐ฉ
    GREEN_SQUARE(intArrayOf(0x1F7E9)),

    // ๐ฆ ๐ฆ ๐ฆ
    BLUE_SQUARE(intArrayOf(0x1F7E6)),

    // ๐ช ๐ช ๐ช
    PURPLE_SQUARE(intArrayOf(0x1F7EA)),

    // ๐ซ ๐ซ ๐ซ
    BROWN_SQUARE(intArrayOf(0x1F7EB)),

    // โฌ โฌ โฌ
    BLACK_LARGE_SQUARE(intArrayOf(0x2B1B)),

    // โฌ โฌ โฌ
    WHITE_LARGE_SQUARE(intArrayOf(0x2B1C)),

    // โผ๏ธ โผ๏ธ โผ๏ธ
    BLACK_MEDIUM_SQUARE(intArrayOf(0x25FC, 0xFE0F)),

    // โป๏ธ โป๏ธ โป๏ธ
    WHITE_MEDIUM_SQUARE(intArrayOf(0x25FB, 0xFE0F)),

    // โพ โพ โพ
    BLACK_MEDIUM_SMALL_SQUARE(intArrayOf(0x25FE)),

    // โฝ โฝ โฝ
    WHITE_MEDIUM_SMALL_SQUARE(intArrayOf(0x25FD)),

    // โช๏ธ โช๏ธ โช๏ธ
    BLACK_SMALL_SQUARE(intArrayOf(0x25AA, 0xFE0F)),

    // โซ๏ธ โซ๏ธ โซ๏ธ
    WHITE_SMALL_SQUARE(intArrayOf(0x25AB, 0xFE0F)),

    // ๐ถ ๐ถ ๐ถ
    LARGE_ORANGE_DIAMOND(intArrayOf(0x1F536)),

    // ๐ท ๐ท ๐ท
    LARGE_BLUE_DIAMOND(intArrayOf(0x1F537)),

    // ๐ธ ๐ธ ๐ธ
    SMALL_ORANGE_DIAMOND(intArrayOf(0x1F538)),

    // ๐น ๐น ๐น
    SMALL_BLUE_DIAMOND(intArrayOf(0x1F539)),

    // ๐บ ๐บ ๐บ
    RED_TRIANGLE_POINTED_UP(intArrayOf(0x1F53A)),

    // ๐ป ๐ป ๐ป
    RED_TRIANGLE_POINTED_DOWN(intArrayOf(0x1F53B)),

    // ๐? ๐? ๐?
    DIAMOND_WITH_A_DOT(intArrayOf(0x1F4A0)),

    // ๐ ๐ ๐
    RADIO_BUTTON(intArrayOf(0x1F518)),

    // ๐ณ ๐ณ ๐ณ
    WHITE_SQUARE_BUTTON(intArrayOf(0x1F533)),

    // ๐ฒ ๐ฒ ๐ฒ
    BLACK_SQUARE_BUTTON(intArrayOf(0x1F532)),

    // ๐ ๐ ๐
    CHEQUERED_FLAG(intArrayOf(0x1F3C1)),

    // ๐ฉ ๐ฉ ๐ฉ
    TRIANGULAR_FLAG(intArrayOf(0x1F6A9)),

    // ๐ ๐ ๐
    CROSSED_FLAGS(intArrayOf(0x1F38C)),

    // ๐ด ๐ด ๐ด
    BLACK_FLAG(intArrayOf(0x1F3F4)),

    // ๐ณ๏ธ ๐ณ๏ธ ๐ณ๏ธ
    WHITE_FLAG(intArrayOf(0x1F3F3, 0xFE0F)),

    // ๐ณ๏ธโ๐ ๐ณ๏ธโ๐ ๐ณ๏ธโ๐
    RAINBOW_FLAG(intArrayOf(0x1F3F3, 0xFE0F, 0x200D, 0x1F308)),

    // ๐ณ๏ธโโง๏ธ ๐ณ๏ธโโง๏ธ ๐ณ๏ธโโง๏ธ
    TRANSGENDER_FLAG(intArrayOf(0x1F3F3, 0xFE0F, 0x200D, 0x26A7, 0xFE0F)),

    // ๐ดโโ?๏ธ ๐ดโโ?๏ธ ๐ดโโ?๏ธ
    PIRATE_FLAG(intArrayOf(0x1F3F4, 0x200D, 0x2620, 0xFE0F)),

    // ๐ฆ๐จ ๐ฆ๐จ ๐ฆ๐จ
    FLAG_ASCENSION_ISLAND(intArrayOf(0x1F1E6, 0x1F1E8)),

    // ๐ฆ๐ฉ ๐ฆ๐ฉ ๐ฆ๐ฉ
    FLAG_ANDORRA(intArrayOf(0x1F1E6, 0x1F1E9)),

    // ๐ฆ๐ช ๐ฆ๐ช ๐ฆ๐ช
    FLAG_UNITED_ARAB_EMIRATES(intArrayOf(0x1F1E6, 0x1F1EA)),

    // ๐ฆ๐ซ ๐ฆ๐ซ ๐ฆ๐ซ
    FLAG_AFGHANISTAN(intArrayOf(0x1F1E6, 0x1F1EB)),

    // ๐ฆ๐ฌ ๐ฆ๐ฌ ๐ฆ๐ฌ
    FLAG_ANTIGUA_AND_BARBUDA(intArrayOf(0x1F1E6, 0x1F1EC)),

    // ๐ฆ๐ฎ ๐ฆ๐ฎ ๐ฆ๐ฎ
    FLAG_ANGUILLA(intArrayOf(0x1F1E6, 0x1F1EE)),

    // ๐ฆ๐ฑ ๐ฆ๐ฑ ๐ฆ๐ฑ
    FLAG_ALBANIA(intArrayOf(0x1F1E6, 0x1F1F1)),

    // ๐ฆ๐ฒ ๐ฆ๐ฒ ๐ฆ๐ฒ
    FLAG_ARMENIA(intArrayOf(0x1F1E6, 0x1F1F2)),

    // ๐ฆ๐ด ๐ฆ๐ด ๐ฆ๐ด
    FLAG_ANGOLA(intArrayOf(0x1F1E6, 0x1F1F4)),

    // ๐ฆ๐ถ ๐ฆ๐ถ ๐ฆ๐ถ
    FLAG_ANTARCTICA(intArrayOf(0x1F1E6, 0x1F1F6)),

    // ๐ฆ๐ท ๐ฆ๐ท ๐ฆ๐ท
    FLAG_ARGENTINA(intArrayOf(0x1F1E6, 0x1F1F7)),

    // ๐ฆ๐ธ ๐ฆ๐ธ ๐ฆ๐ธ
    FLAG_AMERICAN_SAMOA(intArrayOf(0x1F1E6, 0x1F1F8)),

    // ๐ฆ๐น ๐ฆ๐น ๐ฆ๐น
    FLAG_AUSTRIA(intArrayOf(0x1F1E6, 0x1F1F9)),

    // ๐ฆ๐บ ๐ฆ๐บ ๐ฆ๐บ
    FLAG_AUSTRALIA(intArrayOf(0x1F1E6, 0x1F1FA)),

    // ๐ฆ๐ผ ๐ฆ๐ผ ๐ฆ๐ผ
    FLAG_ARUBA(intArrayOf(0x1F1E6, 0x1F1FC)),

    // ๐ฆ๐ฝ ๐ฆ๐ฝ ๐ฆ๐ฝ
    FLAG_รLAND_ISLANDS(intArrayOf(0x1F1E6, 0x1F1FD)),

    // ๐ฆ๐ฟ ๐ฆ๐ฟ ๐ฆ๐ฟ
    FLAG_AZERBAIJAN(intArrayOf(0x1F1E6, 0x1F1FF)),

    // ๐ง๐ฆ ๐ง๐ฆ ๐ง๐ฆ
    FLAG_BOSNIA_AND_HERZEGOVINA(intArrayOf(0x1F1E7, 0x1F1E6)),

    // ๐ง๐ง ๐ง๐ง ๐ง๐ง
    FLAG_BARBADOS(intArrayOf(0x1F1E7, 0x1F1E7)),

    // ๐ง๐ฉ ๐ง๐ฉ ๐ง๐ฉ
    FLAG_BANGLADESH(intArrayOf(0x1F1E7, 0x1F1E9)),

    // ๐ง๐ช ๐ง๐ช ๐ง๐ช
    FLAG_BELGIUM(intArrayOf(0x1F1E7, 0x1F1EA)),

    // ๐ง๐ซ ๐ง๐ซ ๐ง๐ซ
    FLAG_BURKINA_FASO(intArrayOf(0x1F1E7, 0x1F1EB)),

    // ๐ง๐ฌ ๐ง๐ฌ ๐ง๐ฌ
    FLAG_BULGARIA(intArrayOf(0x1F1E7, 0x1F1EC)),

    // ๐ง๐ญ ๐ง๐ญ ๐ง๐ญ
    FLAG_BAHRAIN(intArrayOf(0x1F1E7, 0x1F1ED)),

    // ๐ง๐ฎ ๐ง๐ฎ ๐ง๐ฎ
    FLAG_BURUNDI(intArrayOf(0x1F1E7, 0x1F1EE)),

    // ๐ง๐ฏ ๐ง๐ฏ ๐ง๐ฏ
    FLAG_BENIN(intArrayOf(0x1F1E7, 0x1F1EF)),

    // ๐ง๐ฑ ๐ง๐ฑ ๐ง๐ฑ
    FLAG_ST_BARTHรLEMY(intArrayOf(0x1F1E7, 0x1F1F1)),

    // ๐ง๐ฒ ๐ง๐ฒ ๐ง๐ฒ
    FLAG_BERMUDA(intArrayOf(0x1F1E7, 0x1F1F2)),

    // ๐ง๐ณ ๐ง๐ณ ๐ง๐ณ
    FLAG_BRUNEI(intArrayOf(0x1F1E7, 0x1F1F3)),

    // ๐ง๐ด ๐ง๐ด ๐ง๐ด
    FLAG_BOLIVIA(intArrayOf(0x1F1E7, 0x1F1F4)),

    // ๐ง๐ถ ๐ง๐ถ ๐ง๐ถ
    FLAG_CARIBBEAN_NETHERLANDS(intArrayOf(0x1F1E7, 0x1F1F6)),

    // ๐ง๐ท ๐ง๐ท ๐ง๐ท
    FLAG_BRAZIL(intArrayOf(0x1F1E7, 0x1F1F7)),

    // ๐ง๐ธ ๐ง๐ธ ๐ง๐ธ
    FLAG_BAHAMAS(intArrayOf(0x1F1E7, 0x1F1F8)),

    // ๐ง๐น ๐ง๐น ๐ง๐น
    FLAG_BHUTAN(intArrayOf(0x1F1E7, 0x1F1F9)),

    // ๐ง๐ป ๐ง๐ป ๐ง๐ป
    FLAG_BOUVET_ISLAND(intArrayOf(0x1F1E7, 0x1F1FB)),

    // ๐ง๐ผ ๐ง๐ผ ๐ง๐ผ
    FLAG_BOTSWANA(intArrayOf(0x1F1E7, 0x1F1FC)),

    // ๐ง๐พ ๐ง๐พ ๐ง๐พ
    FLAG_BELARUS(intArrayOf(0x1F1E7, 0x1F1FE)),

    // ๐ง๐ฟ ๐ง๐ฟ ๐ง๐ฟ
    FLAG_BELIZE(intArrayOf(0x1F1E7, 0x1F1FF)),

    // ๐จ๐ฆ ๐จ๐ฆ ๐จ๐ฆ
    FLAG_CANADA(intArrayOf(0x1F1E8, 0x1F1E6)),

    // ๐จ๐จ ๐จ๐จ ๐จ๐จ
    FLAG_COCOS_KEELING_ISLANDS(intArrayOf(0x1F1E8, 0x1F1E8)),

    // ๐จ๐ฉ ๐จ๐ฉ ๐จ๐ฉ
    FLAG_CONGO___KINSHASA(intArrayOf(0x1F1E8, 0x1F1E9)),

    // ๐จ๐ซ ๐จ๐ซ ๐จ๐ซ
    FLAG_CENTRAL_AFRICAN_REPUBLIC(intArrayOf(0x1F1E8, 0x1F1EB)),

    // ๐จ๐ฌ ๐จ๐ฌ ๐จ๐ฌ
    FLAG_CONGO___BRAZZAVILLE(intArrayOf(0x1F1E8, 0x1F1EC)),

    // ๐จ๐ญ ๐จ๐ญ ๐จ๐ญ
    FLAG_SWITZERLAND(intArrayOf(0x1F1E8, 0x1F1ED)),

    // ๐จ๐ฎ ๐จ๐ฎ ๐จ๐ฎ
    FLAG_CรTE_DIVOIRE(intArrayOf(0x1F1E8, 0x1F1EE)),

    // ๐จ๐ฐ ๐จ๐ฐ ๐จ๐ฐ
    FLAG_COOK_ISLANDS(intArrayOf(0x1F1E8, 0x1F1F0)),

    // ๐จ๐ฑ ๐จ๐ฑ ๐จ๐ฑ
    FLAG_CHILE(intArrayOf(0x1F1E8, 0x1F1F1)),

    // ๐จ๐ฒ ๐จ๐ฒ ๐จ๐ฒ
    FLAG_CAMEROON(intArrayOf(0x1F1E8, 0x1F1F2)),

    // ๐จ๐ณ ๐จ๐ณ ๐จ๐ณ
    FLAG_CHINA(intArrayOf(0x1F1E8, 0x1F1F3)),

    // ๐จ๐ด ๐จ๐ด ๐จ๐ด
    FLAG_COLOMBIA(intArrayOf(0x1F1E8, 0x1F1F4)),

    // ๐จ๐ต ๐จ๐ต ๐จ๐ต
    FLAG_CLIPPERTON_ISLAND(intArrayOf(0x1F1E8, 0x1F1F5)),

    // ๐จ๐ท ๐จ๐ท ๐จ๐ท
    FLAG_COSTA_RICA(intArrayOf(0x1F1E8, 0x1F1F7)),

    // ๐จ๐บ ๐จ๐บ ๐จ๐บ
    FLAG_CUBA(intArrayOf(0x1F1E8, 0x1F1FA)),

    // ๐จ๐ป ๐จ๐ป ๐จ๐ป
    FLAG_CAPE_VERDE(intArrayOf(0x1F1E8, 0x1F1FB)),

    // ๐จ๐ผ ๐จ๐ผ ๐จ๐ผ
    FLAG_CURAรAO(intArrayOf(0x1F1E8, 0x1F1FC)),

    // ๐จ๐ฝ ๐จ๐ฝ ๐จ๐ฝ
    FLAG_CHRISTMAS_ISLAND(intArrayOf(0x1F1E8, 0x1F1FD)),

    // ๐จ๐พ ๐จ๐พ ๐จ๐พ
    FLAG_CYPRUS(intArrayOf(0x1F1E8, 0x1F1FE)),

    // ๐จ๐ฟ ๐จ๐ฟ ๐จ๐ฟ
    FLAG_CZECHIA(intArrayOf(0x1F1E8, 0x1F1FF)),

    // ๐ฉ๐ช ๐ฉ๐ช ๐ฉ๐ช
    FLAG_GERMANY(intArrayOf(0x1F1E9, 0x1F1EA)),

    // ๐ฉ๐ฌ ๐ฉ๐ฌ ๐ฉ๐ฌ
    FLAG_DIEGO_GARCIA(intArrayOf(0x1F1E9, 0x1F1EC)),

    // ๐ฉ๐ฏ ๐ฉ๐ฏ ๐ฉ๐ฏ
    FLAG_DJIBOUTI(intArrayOf(0x1F1E9, 0x1F1EF)),

    // ๐ฉ๐ฐ ๐ฉ๐ฐ ๐ฉ๐ฐ
    FLAG_DENMARK(intArrayOf(0x1F1E9, 0x1F1F0)),

    // ๐ฉ๐ฒ ๐ฉ๐ฒ ๐ฉ๐ฒ
    FLAG_DOMINICA(intArrayOf(0x1F1E9, 0x1F1F2)),

    // ๐ฉ๐ด ๐ฉ๐ด ๐ฉ๐ด
    FLAG_DOMINICAN_REPUBLIC(intArrayOf(0x1F1E9, 0x1F1F4)),

    // ๐ฉ๐ฟ ๐ฉ๐ฟ ๐ฉ๐ฟ
    FLAG_ALGERIA(intArrayOf(0x1F1E9, 0x1F1FF)),

    // ๐ช๐ฆ ๐ช๐ฆ ๐ช๐ฆ
    FLAG_CEUTA_AND_MELILLA(intArrayOf(0x1F1EA, 0x1F1E6)),

    // ๐ช๐จ ๐ช๐จ ๐ช๐จ
    FLAG_ECUADOR(intArrayOf(0x1F1EA, 0x1F1E8)),

    // ๐ช๐ช ๐ช๐ช ๐ช๐ช
    FLAG_ESTONIA(intArrayOf(0x1F1EA, 0x1F1EA)),

    // ๐ช๐ฌ ๐ช๐ฌ ๐ช๐ฌ
    FLAG_EGYPT(intArrayOf(0x1F1EA, 0x1F1EC)),

    // ๐ช๐ญ ๐ช๐ญ ๐ช๐ญ
    FLAG_WESTERN_SAHARA(intArrayOf(0x1F1EA, 0x1F1ED)),

    // ๐ช๐ท ๐ช๐ท ๐ช๐ท
    FLAG_ERITREA(intArrayOf(0x1F1EA, 0x1F1F7)),

    // ๐ช๐ธ ๐ช๐ธ ๐ช๐ธ
    FLAG_SPAIN(intArrayOf(0x1F1EA, 0x1F1F8)),

    // ๐ช๐น ๐ช๐น ๐ช๐น
    FLAG_ETHIOPIA(intArrayOf(0x1F1EA, 0x1F1F9)),

    // ๐ช๐บ ๐ช๐บ ๐ช๐บ
    FLAG_EUROPEAN_UNION(intArrayOf(0x1F1EA, 0x1F1FA)),

    // ๐ซ๐ฎ ๐ซ๐ฎ ๐ซ๐ฎ
    FLAG_FINLAND(intArrayOf(0x1F1EB, 0x1F1EE)),

    // ๐ซ๐ฏ ๐ซ๐ฏ ๐ซ๐ฏ
    FLAG_FIJI(intArrayOf(0x1F1EB, 0x1F1EF)),

    // ๐ซ๐ฐ ๐ซ๐ฐ ๐ซ๐ฐ
    FLAG_FALKLAND_ISLANDS(intArrayOf(0x1F1EB, 0x1F1F0)),

    // ๐ซ๐ฒ ๐ซ๐ฒ ๐ซ๐ฒ
    FLAG_MICRONESIA(intArrayOf(0x1F1EB, 0x1F1F2)),

    // ๐ซ๐ด ๐ซ๐ด ๐ซ๐ด
    FLAG_FAROE_ISLANDS(intArrayOf(0x1F1EB, 0x1F1F4)),

    // ๐ซ๐ท ๐ซ๐ท ๐ซ๐ท
    FLAG_FRANCE(intArrayOf(0x1F1EB, 0x1F1F7)),

    // ๐ฌ๐ฆ ๐ฌ๐ฆ ๐ฌ๐ฆ
    FLAG_GABON(intArrayOf(0x1F1EC, 0x1F1E6)),

    // ๐ฌ๐ง ๐ฌ๐ง ๐ฌ๐ง
    FLAG_UNITED_KINGDOM(intArrayOf(0x1F1EC, 0x1F1E7)),

    // ๐ฌ๐ฉ ๐ฌ๐ฉ ๐ฌ๐ฉ
    FLAG_GRENADA(intArrayOf(0x1F1EC, 0x1F1E9)),

    // ๐ฌ๐ช ๐ฌ๐ช ๐ฌ๐ช
    FLAG_GEORGIA(intArrayOf(0x1F1EC, 0x1F1EA)),

    // ๐ฌ๐ซ ๐ฌ๐ซ ๐ฌ๐ซ
    FLAG_FRENCH_GUIANA(intArrayOf(0x1F1EC, 0x1F1EB)),

    // ๐ฌ๐ฌ ๐ฌ๐ฌ ๐ฌ๐ฌ
    FLAG_GUERNSEY(intArrayOf(0x1F1EC, 0x1F1EC)),

    // ๐ฌ๐ญ ๐ฌ๐ญ ๐ฌ๐ญ
    FLAG_GHANA(intArrayOf(0x1F1EC, 0x1F1ED)),

    // ๐ฌ๐ฎ ๐ฌ๐ฎ ๐ฌ๐ฎ
    FLAG_GIBRALTAR(intArrayOf(0x1F1EC, 0x1F1EE)),

    // ๐ฌ๐ฑ ๐ฌ๐ฑ ๐ฌ๐ฑ
    FLAG_GREENLAND(intArrayOf(0x1F1EC, 0x1F1F1)),

    // ๐ฌ๐ฒ ๐ฌ๐ฒ ๐ฌ๐ฒ
    FLAG_GAMBIA(intArrayOf(0x1F1EC, 0x1F1F2)),

    // ๐ฌ๐ณ ๐ฌ๐ณ ๐ฌ๐ณ
    FLAG_GUINEA(intArrayOf(0x1F1EC, 0x1F1F3)),

    // ๐ฌ๐ต ๐ฌ๐ต ๐ฌ๐ต
    FLAG_GUADELOUPE(intArrayOf(0x1F1EC, 0x1F1F5)),

    // ๐ฌ๐ถ ๐ฌ๐ถ ๐ฌ๐ถ
    FLAG_EQUATORIAL_GUINEA(intArrayOf(0x1F1EC, 0x1F1F6)),

    // ๐ฌ๐ท ๐ฌ๐ท ๐ฌ๐ท
    FLAG_GREECE(intArrayOf(0x1F1EC, 0x1F1F7)),

    // ๐ฌ๐ธ ๐ฌ๐ธ ๐ฌ๐ธ
    FLAG_SOUTH_GEORGIA_AND_SOUTH_SANDWICH_ISLANDS(intArrayOf(0x1F1EC, 0x1F1F8)),

    // ๐ฌ๐น ๐ฌ๐น ๐ฌ๐น
    FLAG_GUATEMALA(intArrayOf(0x1F1EC, 0x1F1F9)),

    // ๐ฌ๐บ ๐ฌ๐บ ๐ฌ๐บ
    FLAG_GUAM(intArrayOf(0x1F1EC, 0x1F1FA)),

    // ๐ฌ๐ผ ๐ฌ๐ผ ๐ฌ๐ผ
    FLAG_GUINEA_BISSAU(intArrayOf(0x1F1EC, 0x1F1FC)),

    // ๐ฌ๐พ ๐ฌ๐พ ๐ฌ๐พ
    FLAG_GUYANA(intArrayOf(0x1F1EC, 0x1F1FE)),

    // ๐ญ๐ฐ ๐ญ๐ฐ ๐ญ๐ฐ
    FLAG_HONG_KONG_SAR_CHINA(intArrayOf(0x1F1ED, 0x1F1F0)),

    // ๐ญ๐ฒ ๐ญ๐ฒ ๐ญ๐ฒ
    FLAG_HEARD_AND_MCDONALD_ISLANDS(intArrayOf(0x1F1ED, 0x1F1F2)),

    // ๐ญ๐ณ ๐ญ๐ณ ๐ญ๐ณ
    FLAG_HONDURAS(intArrayOf(0x1F1ED, 0x1F1F3)),

    // ๐ญ๐ท ๐ญ๐ท ๐ญ๐ท
    FLAG_CROATIA(intArrayOf(0x1F1ED, 0x1F1F7)),

    // ๐ญ๐น ๐ญ๐น ๐ญ๐น
    FLAG_HAITI(intArrayOf(0x1F1ED, 0x1F1F9)),

    // ๐ญ๐บ ๐ญ๐บ ๐ญ๐บ
    FLAG_HUNGARY(intArrayOf(0x1F1ED, 0x1F1FA)),

    // ๐ฎ๐จ ๐ฎ๐จ ๐ฎ๐จ
    FLAG_CANARY_ISLANDS(intArrayOf(0x1F1EE, 0x1F1E8)),

    // ๐ฎ๐ฉ ๐ฎ๐ฉ ๐ฎ๐ฉ
    FLAG_INDONESIA(intArrayOf(0x1F1EE, 0x1F1E9)),

    // ๐ฎ๐ช ๐ฎ๐ช ๐ฎ๐ช
    FLAG_IRELAND(intArrayOf(0x1F1EE, 0x1F1EA)),

    // ๐ฎ๐ฑ ๐ฎ๐ฑ ๐ฎ๐ฑ
    FLAG_ISRAEL(intArrayOf(0x1F1EE, 0x1F1F1)),

    // ๐ฎ๐ฒ ๐ฎ๐ฒ ๐ฎ๐ฒ
    FLAG_ISLE_OF_MAN(intArrayOf(0x1F1EE, 0x1F1F2)),

    // ๐ฎ๐ณ ๐ฎ๐ณ ๐ฎ๐ณ
    FLAG_INDIA(intArrayOf(0x1F1EE, 0x1F1F3)),

    // ๐ฎ๐ด ๐ฎ๐ด ๐ฎ๐ด
    FLAG_BRITISH_INDIAN_OCEAN_TERRITORY(intArrayOf(0x1F1EE, 0x1F1F4)),

    // ๐ฎ๐ถ ๐ฎ๐ถ ๐ฎ๐ถ
    FLAG_IRAQ(intArrayOf(0x1F1EE, 0x1F1F6)),

    // ๐ฎ๐ท ๐ฎ๐ท ๐ฎ๐ท
    FLAG_IRAN(intArrayOf(0x1F1EE, 0x1F1F7)),

    // ๐ฎ๐ธ ๐ฎ๐ธ ๐ฎ๐ธ
    FLAG_ICELAND(intArrayOf(0x1F1EE, 0x1F1F8)),

    // ๐ฎ๐น ๐ฎ๐น ๐ฎ๐น
    FLAG_ITALY(intArrayOf(0x1F1EE, 0x1F1F9)),

    // ๐ฏ๐ช ๐ฏ๐ช ๐ฏ๐ช
    FLAG_JERSEY(intArrayOf(0x1F1EF, 0x1F1EA)),

    // ๐ฏ๐ฒ ๐ฏ๐ฒ ๐ฏ๐ฒ
    FLAG_JAMAICA(intArrayOf(0x1F1EF, 0x1F1F2)),

    // ๐ฏ๐ด ๐ฏ๐ด ๐ฏ๐ด
    FLAG_JORDAN(intArrayOf(0x1F1EF, 0x1F1F4)),

    // ๐ฏ๐ต ๐ฏ๐ต ๐ฏ๐ต
    FLAG_JAPAN(intArrayOf(0x1F1EF, 0x1F1F5)),

    // ๐ฐ๐ช ๐ฐ๐ช ๐ฐ๐ช
    FLAG_KENYA(intArrayOf(0x1F1F0, 0x1F1EA)),

    // ๐ฐ๐ฌ ๐ฐ๐ฌ ๐ฐ๐ฌ
    FLAG_KYRGYZSTAN(intArrayOf(0x1F1F0, 0x1F1EC)),

    // ๐ฐ๐ญ ๐ฐ๐ญ ๐ฐ๐ญ
    FLAG_CAMBODIA(intArrayOf(0x1F1F0, 0x1F1ED)),

    // ๐ฐ๐ฎ ๐ฐ๐ฎ ๐ฐ๐ฎ
    FLAG_KIRIBATI(intArrayOf(0x1F1F0, 0x1F1EE)),

    // ๐ฐ๐ฒ ๐ฐ๐ฒ ๐ฐ๐ฒ
    FLAG_COMOROS(intArrayOf(0x1F1F0, 0x1F1F2)),

    // ๐ฐ๐ณ ๐ฐ๐ณ ๐ฐ๐ณ
    FLAG_ST_KITTS_AND_NEVIS(intArrayOf(0x1F1F0, 0x1F1F3)),

    // ๐ฐ๐ต ๐ฐ๐ต ๐ฐ๐ต
    FLAG_NORTH_KOREA(intArrayOf(0x1F1F0, 0x1F1F5)),

    // ๐ฐ๐ท ๐ฐ๐ท ๐ฐ๐ท
    FLAG_SOUTH_KOREA(intArrayOf(0x1F1F0, 0x1F1F7)),

    // ๐ฐ๐ผ ๐ฐ๐ผ ๐ฐ๐ผ
    FLAG_KUWAIT(intArrayOf(0x1F1F0, 0x1F1FC)),

    // ๐ฐ๐พ ๐ฐ๐พ ๐ฐ๐พ
    FLAG_CAYMAN_ISLANDS(intArrayOf(0x1F1F0, 0x1F1FE)),

    // ๐ฐ๐ฟ ๐ฐ๐ฟ ๐ฐ๐ฟ
    FLAG_KAZAKHSTAN(intArrayOf(0x1F1F0, 0x1F1FF)),

    // ๐ฑ๐ฆ ๐ฑ๐ฆ ๐ฑ๐ฆ
    FLAG_LAOS(intArrayOf(0x1F1F1, 0x1F1E6)),

    // ๐ฑ๐ง ๐ฑ๐ง ๐ฑ๐ง
    FLAG_LEBANON(intArrayOf(0x1F1F1, 0x1F1E7)),

    // ๐ฑ๐จ ๐ฑ๐จ ๐ฑ๐จ
    FLAG_ST_LUCIA(intArrayOf(0x1F1F1, 0x1F1E8)),

    // ๐ฑ๐ฎ ๐ฑ๐ฎ ๐ฑ๐ฎ
    FLAG_LIECHTENSTEIN(intArrayOf(0x1F1F1, 0x1F1EE)),

    // ๐ฑ๐ฐ ๐ฑ๐ฐ ๐ฑ๐ฐ
    FLAG_SRI_LANKA(intArrayOf(0x1F1F1, 0x1F1F0)),

    // ๐ฑ๐ท ๐ฑ๐ท ๐ฑ๐ท
    FLAG_LIBERIA(intArrayOf(0x1F1F1, 0x1F1F7)),

    // ๐ฑ๐ธ ๐ฑ๐ธ ๐ฑ๐ธ
    FLAG_LESOTHO(intArrayOf(0x1F1F1, 0x1F1F8)),

    // ๐ฑ๐น ๐ฑ๐น ๐ฑ๐น
    FLAG_LITHUANIA(intArrayOf(0x1F1F1, 0x1F1F9)),

    // ๐ฑ๐บ ๐ฑ๐บ ๐ฑ๐บ
    FLAG_LUXEMBOURG(intArrayOf(0x1F1F1, 0x1F1FA)),

    // ๐ฑ๐ป ๐ฑ๐ป ๐ฑ๐ป
    FLAG_LATVIA(intArrayOf(0x1F1F1, 0x1F1FB)),

    // ๐ฑ๐พ ๐ฑ๐พ ๐ฑ๐พ
    FLAG_LIBYA(intArrayOf(0x1F1F1, 0x1F1FE)),

    // ๐ฒ๐ฆ ๐ฒ๐ฆ ๐ฒ๐ฆ
    FLAG_MOROCCO(intArrayOf(0x1F1F2, 0x1F1E6)),

    // ๐ฒ๐จ ๐ฒ๐จ ๐ฒ๐จ
    FLAG_MONACO(intArrayOf(0x1F1F2, 0x1F1E8)),

    // ๐ฒ๐ฉ ๐ฒ๐ฉ ๐ฒ๐ฉ
    FLAG_MOLDOVA(intArrayOf(0x1F1F2, 0x1F1E9)),

    // ๐ฒ๐ช ๐ฒ๐ช ๐ฒ๐ช
    FLAG_MONTENEGRO(intArrayOf(0x1F1F2, 0x1F1EA)),

    // ๐ฒ๐ซ ๐ฒ๐ซ ๐ฒ๐ซ
    FLAG_ST_MARTIN(intArrayOf(0x1F1F2, 0x1F1EB)),

    // ๐ฒ๐ฌ ๐ฒ๐ฌ ๐ฒ๐ฌ
    FLAG_MADAGASCAR(intArrayOf(0x1F1F2, 0x1F1EC)),

    // ๐ฒ๐ญ ๐ฒ๐ญ ๐ฒ๐ญ
    FLAG_MARSHALL_ISLANDS(intArrayOf(0x1F1F2, 0x1F1ED)),

    // ๐ฒ๐ฐ ๐ฒ๐ฐ ๐ฒ๐ฐ
    FLAG_NORTH_MACEDONIA(intArrayOf(0x1F1F2, 0x1F1F0)),

    // ๐ฒ๐ฑ ๐ฒ๐ฑ ๐ฒ๐ฑ
    FLAG_MALI(intArrayOf(0x1F1F2, 0x1F1F1)),

    // ๐ฒ๐ฒ ๐ฒ๐ฒ ๐ฒ๐ฒ
    FLAG_MYANMAR_BURMA(intArrayOf(0x1F1F2, 0x1F1F2)),

    // ๐ฒ๐ณ ๐ฒ๐ณ ๐ฒ๐ณ
    FLAG_MONGOLIA(intArrayOf(0x1F1F2, 0x1F1F3)),

    // ๐ฒ๐ด ๐ฒ๐ด ๐ฒ๐ด
    FLAG_MACAO_SAR_CHINA(intArrayOf(0x1F1F2, 0x1F1F4)),

    // ๐ฒ๐ต ๐ฒ๐ต ๐ฒ๐ต
    FLAG_NORTHERN_MARIANA_ISLANDS(intArrayOf(0x1F1F2, 0x1F1F5)),

    // ๐ฒ๐ถ ๐ฒ๐ถ ๐ฒ๐ถ
    FLAG_MARTINIQUE(intArrayOf(0x1F1F2, 0x1F1F6)),

    // ๐ฒ๐ท ๐ฒ๐ท ๐ฒ๐ท
    FLAG_MAURITANIA(intArrayOf(0x1F1F2, 0x1F1F7)),

    // ๐ฒ๐ธ ๐ฒ๐ธ ๐ฒ๐ธ
    FLAG_MONTSERRAT(intArrayOf(0x1F1F2, 0x1F1F8)),

    // ๐ฒ๐น ๐ฒ๐น ๐ฒ๐น
    FLAG_MALTA(intArrayOf(0x1F1F2, 0x1F1F9)),

    // ๐ฒ๐บ ๐ฒ๐บ ๐ฒ๐บ
    FLAG_MAURITIUS(intArrayOf(0x1F1F2, 0x1F1FA)),

    // ๐ฒ๐ป ๐ฒ๐ป ๐ฒ๐ป
    FLAG_MALDIVES(intArrayOf(0x1F1F2, 0x1F1FB)),

    // ๐ฒ๐ผ ๐ฒ๐ผ ๐ฒ๐ผ
    FLAG_MALAWI(intArrayOf(0x1F1F2, 0x1F1FC)),

    // ๐ฒ๐ฝ ๐ฒ๐ฝ ๐ฒ๐ฝ
    FLAG_MEXICO(intArrayOf(0x1F1F2, 0x1F1FD)),

    // ๐ฒ๐พ ๐ฒ๐พ ๐ฒ๐พ
    FLAG_MALAYSIA(intArrayOf(0x1F1F2, 0x1F1FE)),

    // ๐ฒ๐ฟ ๐ฒ๐ฟ ๐ฒ๐ฟ
    FLAG_MOZAMBIQUE(intArrayOf(0x1F1F2, 0x1F1FF)),

    // ๐ณ๐ฆ ๐ณ๐ฆ ๐ณ๐ฆ
    FLAG_NAMIBIA(intArrayOf(0x1F1F3, 0x1F1E6)),

    // ๐ณ๐จ ๐ณ๐จ ๐ณ๐จ
    FLAG_NEW_CALEDONIA(intArrayOf(0x1F1F3, 0x1F1E8)),

    // ๐ณ๐ช ๐ณ๐ช ๐ณ๐ช
    FLAG_NIGER(intArrayOf(0x1F1F3, 0x1F1EA)),

    // ๐ณ๐ซ ๐ณ๐ซ ๐ณ๐ซ
    FLAG_NORFOLK_ISLAND(intArrayOf(0x1F1F3, 0x1F1EB)),

    // ๐ณ๐ฌ ๐ณ๐ฌ ๐ณ๐ฌ
    FLAG_NIGERIA(intArrayOf(0x1F1F3, 0x1F1EC)),

    // ๐ณ๐ฎ ๐ณ๐ฎ ๐ณ๐ฎ
    FLAG_NICARAGUA(intArrayOf(0x1F1F3, 0x1F1EE)),

    // ๐ณ๐ฑ ๐ณ๐ฑ ๐ณ๐ฑ
    FLAG_NETHERLANDS(intArrayOf(0x1F1F3, 0x1F1F1)),

    // ๐ณ๐ด ๐ณ๐ด ๐ณ๐ด
    FLAG_NORWAY(intArrayOf(0x1F1F3, 0x1F1F4)),

    // ๐ณ๐ต ๐ณ๐ต ๐ณ๐ต
    FLAG_NEPAL(intArrayOf(0x1F1F3, 0x1F1F5)),

    // ๐ณ๐ท ๐ณ๐ท ๐ณ๐ท
    FLAG_NAURU(intArrayOf(0x1F1F3, 0x1F1F7)),

    // ๐ณ๐บ ๐ณ๐บ ๐ณ๐บ
    FLAG_NIUE(intArrayOf(0x1F1F3, 0x1F1FA)),

    // ๐ณ๐ฟ ๐ณ๐ฟ ๐ณ๐ฟ
    FLAG_NEW_ZEALAND(intArrayOf(0x1F1F3, 0x1F1FF)),

    // ๐ด๐ฒ ๐ด๐ฒ ๐ด๐ฒ
    FLAG_OMAN(intArrayOf(0x1F1F4, 0x1F1F2)),

    // ๐ต๐ฆ ๐ต๐ฆ ๐ต๐ฆ
    FLAG_PANAMA(intArrayOf(0x1F1F5, 0x1F1E6)),

    // ๐ต๐ช ๐ต๐ช ๐ต๐ช
    FLAG_PERU(intArrayOf(0x1F1F5, 0x1F1EA)),

    // ๐ต๐ซ ๐ต๐ซ ๐ต๐ซ
    FLAG_FRENCH_POLYNESIA(intArrayOf(0x1F1F5, 0x1F1EB)),

    // ๐ต๐ฌ ๐ต๐ฌ ๐ต๐ฌ
    FLAG_PAPUA_NEW_GUINEA(intArrayOf(0x1F1F5, 0x1F1EC)),

    // ๐ต๐ญ ๐ต๐ญ ๐ต๐ญ
    FLAG_PHILIPPINES(intArrayOf(0x1F1F5, 0x1F1ED)),

    // ๐ต๐ฐ ๐ต๐ฐ ๐ต๐ฐ
    FLAG_PAKISTAN(intArrayOf(0x1F1F5, 0x1F1F0)),

    // ๐ต๐ฑ ๐ต๐ฑ ๐ต๐ฑ
    FLAG_POLAND(intArrayOf(0x1F1F5, 0x1F1F1)),

    // ๐ต๐ฒ ๐ต๐ฒ ๐ต๐ฒ
    FLAG_ST_PIERRE_AND_MIQUELON(intArrayOf(0x1F1F5, 0x1F1F2)),

    // ๐ต๐ณ ๐ต๐ณ ๐ต๐ณ
    FLAG_PITCAIRN_ISLANDS(intArrayOf(0x1F1F5, 0x1F1F3)),

    // ๐ต๐ท ๐ต๐ท ๐ต๐ท
    FLAG_PUERTO_RICO(intArrayOf(0x1F1F5, 0x1F1F7)),

    // ๐ต๐ธ ๐ต๐ธ ๐ต๐ธ
    FLAG_PALESTINIAN_TERRITORIES(intArrayOf(0x1F1F5, 0x1F1F8)),

    // ๐ต๐น ๐ต๐น ๐ต๐น
    FLAG_PORTUGAL(intArrayOf(0x1F1F5, 0x1F1F9)),

    // ๐ต๐ผ ๐ต๐ผ ๐ต๐ผ
    FLAG_PALAU(intArrayOf(0x1F1F5, 0x1F1FC)),

    // ๐ต๐พ ๐ต๐พ ๐ต๐พ
    FLAG_PARAGUAY(intArrayOf(0x1F1F5, 0x1F1FE)),

    // ๐ถ๐ฆ ๐ถ๐ฆ ๐ถ๐ฆ
    FLAG_QATAR(intArrayOf(0x1F1F6, 0x1F1E6)),

    // ๐ท๐ช ๐ท๐ช ๐ท๐ช
    FLAG_RรUNION(intArrayOf(0x1F1F7, 0x1F1EA)),

    // ๐ท๐ด ๐ท๐ด ๐ท๐ด
    FLAG_ROMANIA(intArrayOf(0x1F1F7, 0x1F1F4)),

    // ๐ท๐ธ ๐ท๐ธ ๐ท๐ธ
    FLAG_SERBIA(intArrayOf(0x1F1F7, 0x1F1F8)),

    // ๐ท๐บ ๐ท๐บ ๐ท๐บ
    FLAG_RUSSIA(intArrayOf(0x1F1F7, 0x1F1FA)),

    // ๐ท๐ผ ๐ท๐ผ ๐ท๐ผ
    FLAG_RWANDA(intArrayOf(0x1F1F7, 0x1F1FC)),

    // ๐ธ๐ฆ ๐ธ๐ฆ ๐ธ๐ฆ
    FLAG_SAUDI_ARABIA(intArrayOf(0x1F1F8, 0x1F1E6)),

    // ๐ธ๐ง ๐ธ๐ง ๐ธ๐ง
    FLAG_SOLOMON_ISLANDS(intArrayOf(0x1F1F8, 0x1F1E7)),

    // ๐ธ๐จ ๐ธ๐จ ๐ธ๐จ
    FLAG_SEYCHELLES(intArrayOf(0x1F1F8, 0x1F1E8)),

    // ๐ธ๐ฉ ๐ธ๐ฉ ๐ธ๐ฉ
    FLAG_SUDAN(intArrayOf(0x1F1F8, 0x1F1E9)),

    // ๐ธ๐ช ๐ธ๐ช ๐ธ๐ช
    FLAG_SWEDEN(intArrayOf(0x1F1F8, 0x1F1EA)),

    // ๐ธ๐ฌ ๐ธ๐ฌ ๐ธ๐ฌ
    FLAG_SINGAPORE(intArrayOf(0x1F1F8, 0x1F1EC)),

    // ๐ธ๐ญ ๐ธ๐ญ ๐ธ๐ญ
    FLAG_ST_HELENA(intArrayOf(0x1F1F8, 0x1F1ED)),

    // ๐ธ๐ฎ ๐ธ๐ฎ ๐ธ๐ฎ
    FLAG_SLOVENIA(intArrayOf(0x1F1F8, 0x1F1EE)),

    // ๐ธ๐ฏ ๐ธ๐ฏ ๐ธ๐ฏ
    FLAG_SVALBARD_AND_JAN_MAYEN(intArrayOf(0x1F1F8, 0x1F1EF)),

    // ๐ธ๐ฐ ๐ธ๐ฐ ๐ธ๐ฐ
    FLAG_SLOVAKIA(intArrayOf(0x1F1F8, 0x1F1F0)),

    // ๐ธ๐ฑ ๐ธ๐ฑ ๐ธ๐ฑ
    FLAG_SIERRA_LEONE(intArrayOf(0x1F1F8, 0x1F1F1)),

    // ๐ธ๐ฒ ๐ธ๐ฒ ๐ธ๐ฒ
    FLAG_SAN_MARINO(intArrayOf(0x1F1F8, 0x1F1F2)),

    // ๐ธ๐ณ ๐ธ๐ณ ๐ธ๐ณ
    FLAG_SENEGAL(intArrayOf(0x1F1F8, 0x1F1F3)),

    // ๐ธ๐ด ๐ธ๐ด ๐ธ๐ด
    FLAG_SOMALIA(intArrayOf(0x1F1F8, 0x1F1F4)),

    // ๐ธ๐ท ๐ธ๐ท ๐ธ๐ท
    FLAG_SURINAME(intArrayOf(0x1F1F8, 0x1F1F7)),

    // ๐ธ๐ธ ๐ธ๐ธ ๐ธ๐ธ
    FLAG_SOUTH_SUDAN(intArrayOf(0x1F1F8, 0x1F1F8)),

    // ๐ธ๐น ๐ธ๐น ๐ธ๐น
    FLAG_SรO_TOMร_AND_PRรNCIPE(intArrayOf(0x1F1F8, 0x1F1F9)),

    // ๐ธ๐ป ๐ธ๐ป ๐ธ๐ป
    FLAG_EL_SALVADOR(intArrayOf(0x1F1F8, 0x1F1FB)),

    // ๐ธ๐ฝ ๐ธ๐ฝ ๐ธ๐ฝ
    FLAG_SINT_MAARTEN(intArrayOf(0x1F1F8, 0x1F1FD)),

    // ๐ธ๐พ ๐ธ๐พ ๐ธ๐พ
    FLAG_SYRIA(intArrayOf(0x1F1F8, 0x1F1FE)),

    // ๐ธ๐ฟ ๐ธ๐ฟ ๐ธ๐ฟ
    FLAG_ESWATINI(intArrayOf(0x1F1F8, 0x1F1FF)),

    // ๐น๐ฆ ๐น๐ฆ ๐น๐ฆ
    FLAG_TRISTAN_DA_CUNHA(intArrayOf(0x1F1F9, 0x1F1E6)),

    // ๐น๐จ ๐น๐จ ๐น๐จ
    FLAG_TURKS_AND_CAICOS_ISLANDS(intArrayOf(0x1F1F9, 0x1F1E8)),

    // ๐น๐ฉ ๐น๐ฉ ๐น๐ฉ
    FLAG_CHAD(intArrayOf(0x1F1F9, 0x1F1E9)),

    // ๐น๐ซ ๐น๐ซ ๐น๐ซ
    FLAG_FRENCH_SOUTHERN_TERRITORIES(intArrayOf(0x1F1F9, 0x1F1EB)),

    // ๐น๐ฌ ๐น๐ฌ ๐น๐ฌ
    FLAG_TOGO(intArrayOf(0x1F1F9, 0x1F1EC)),

    // ๐น๐ญ ๐น๐ญ ๐น๐ญ
    FLAG_THAILAND(intArrayOf(0x1F1F9, 0x1F1ED)),

    // ๐น๐ฏ ๐น๐ฏ ๐น๐ฏ
    FLAG_TAJIKISTAN(intArrayOf(0x1F1F9, 0x1F1EF)),

    // ๐น๐ฐ ๐น๐ฐ ๐น๐ฐ
    FLAG_TOKELAU(intArrayOf(0x1F1F9, 0x1F1F0)),

    // ๐น๐ฑ ๐น๐ฑ ๐น๐ฑ
    FLAG_TIMOR_LESTE(intArrayOf(0x1F1F9, 0x1F1F1)),

    // ๐น๐ฒ ๐น๐ฒ ๐น๐ฒ
    FLAG_TURKMENISTAN(intArrayOf(0x1F1F9, 0x1F1F2)),

    // ๐น๐ณ ๐น๐ณ ๐น๐ณ
    FLAG_TUNISIA(intArrayOf(0x1F1F9, 0x1F1F3)),

    // ๐น๐ด ๐น๐ด ๐น๐ด
    FLAG_TONGA(intArrayOf(0x1F1F9, 0x1F1F4)),

    // ๐น๐ท ๐น๐ท ๐น๐ท
    FLAG_TURKEY(intArrayOf(0x1F1F9, 0x1F1F7)),

    // ๐น๐น ๐น๐น ๐น๐น
    FLAG_TRINIDAD_AND_TOBAGO(intArrayOf(0x1F1F9, 0x1F1F9)),

    // ๐น๐ป ๐น๐ป ๐น๐ป
    FLAG_TUVALU(intArrayOf(0x1F1F9, 0x1F1FB)),

    // ๐น๐ผ ๐น๐ผ ๐น๐ผ
    FLAG_TAIWAN(intArrayOf(0x1F1F9, 0x1F1FC)),

    // ๐น๐ฟ ๐น๐ฟ ๐น๐ฟ
    FLAG_TANZANIA(intArrayOf(0x1F1F9, 0x1F1FF)),

    // ๐บ๐ฆ ๐บ๐ฆ ๐บ๐ฆ
    FLAG_UKRAINE(intArrayOf(0x1F1FA, 0x1F1E6)),

    // ๐บ๐ฌ ๐บ๐ฌ ๐บ๐ฌ
    FLAG_UGANDA(intArrayOf(0x1F1FA, 0x1F1EC)),

    // ๐บ๐ฒ ๐บ๐ฒ ๐บ๐ฒ
    FLAG_US_OUTLYING_ISLANDS(intArrayOf(0x1F1FA, 0x1F1F2)),

    // ๐บ๐ณ ๐บ๐ณ ๐บ๐ณ
    FLAG_UNITED_NATIONS(intArrayOf(0x1F1FA, 0x1F1F3)),

    // ๐บ๐ธ ๐บ๐ธ ๐บ๐ธ
    FLAG_UNITED_STATES(intArrayOf(0x1F1FA, 0x1F1F8)),

    // ๐บ๐พ ๐บ๐พ ๐บ๐พ
    FLAG_URUGUAY(intArrayOf(0x1F1FA, 0x1F1FE)),

    // ๐บ๐ฟ ๐บ๐ฟ ๐บ๐ฟ
    FLAG_UZBEKISTAN(intArrayOf(0x1F1FA, 0x1F1FF)),

    // ๐ป๐ฆ ๐ป๐ฆ ๐ป๐ฆ
    FLAG_VATICAN_CITY(intArrayOf(0x1F1FB, 0x1F1E6)),

    // ๐ป๐จ ๐ป๐จ ๐ป๐จ
    FLAG_ST_VINCENT_AND_GRENADINES(intArrayOf(0x1F1FB, 0x1F1E8)),

    // ๐ป๐ช ๐ป๐ช ๐ป๐ช
    FLAG_VENEZUELA(intArrayOf(0x1F1FB, 0x1F1EA)),

    // ๐ป๐ฌ ๐ป๐ฌ ๐ป๐ฌ
    FLAG_BRITISH_VIRGIN_ISLANDS(intArrayOf(0x1F1FB, 0x1F1EC)),

    // ๐ป๐ฎ ๐ป๐ฎ ๐ป๐ฎ
    FLAG_US_VIRGIN_ISLANDS(intArrayOf(0x1F1FB, 0x1F1EE)),

    // ๐ป๐ณ ๐ป๐ณ ๐ป๐ณ
    FLAG_VIETNAM(intArrayOf(0x1F1FB, 0x1F1F3)),

    // ๐ป๐บ ๐ป๐บ ๐ป๐บ
    FLAG_VANUATU(intArrayOf(0x1F1FB, 0x1F1FA)),

    // ๐ผ๐ซ ๐ผ๐ซ ๐ผ๐ซ
    FLAG_WALLIS_AND_FUTUNA(intArrayOf(0x1F1FC, 0x1F1EB)),

    // ๐ผ๐ธ ๐ผ๐ธ ๐ผ๐ธ
    FLAG_SAMOA(intArrayOf(0x1F1FC, 0x1F1F8)),

    // ๐ฝ๐ฐ ๐ฝ๐ฐ ๐ฝ๐ฐ
    FLAG_KOSOVO(intArrayOf(0x1F1FD, 0x1F1F0)),

    // ๐พ๐ช ๐พ๐ช ๐พ๐ช
    FLAG_YEMEN(intArrayOf(0x1F1FE, 0x1F1EA)),

    // ๐พ๐น ๐พ๐น ๐พ๐น
    FLAG_MAYOTTE(intArrayOf(0x1F1FE, 0x1F1F9)),

    // ๐ฟ๐ฆ ๐ฟ๐ฆ ๐ฟ๐ฆ
    FLAG_SOUTH_AFRICA(intArrayOf(0x1F1FF, 0x1F1E6)),

    // ๐ฟ๐ฒ ๐ฟ๐ฒ ๐ฟ๐ฒ
    FLAG_ZAMBIA(intArrayOf(0x1F1FF, 0x1F1F2)),

    // ๐ฟ๐ผ ๐ฟ๐ผ ๐ฟ๐ผ
    FLAG_ZIMBABWE(intArrayOf(0x1F1FF, 0x1F1FC)),

    // ๐ด๓?ง๓?ข๓?ฅ๓?ฎ๓?ง๓?ฟ ๐ด๓?ง๓?ข๓?ฅ๓?ฎ๓?ง๓?ฟ ๐ด๓?ง๓?ข๓?ฅ๓?ฎ๓?ง๓?ฟ
    FLAG_ENGLAND(intArrayOf(0x1F3F4, 0xE0067, 0xE0062, 0xE0065, 0xE006E, 0xE0067, 0xE007F)),

    // ๐ด๓?ง๓?ข๓?ณ๓?ฃ๓?ด๓?ฟ ๐ด๓?ง๓?ข๓?ณ๓?ฃ๓?ด๓?ฟ ๐ด๓?ง๓?ข๓?ณ๓?ฃ๓?ด๓?ฟ
    FLAG_SCOTLAND(intArrayOf(0x1F3F4, 0xE0067, 0xE0062, 0xE0073, 0xE0063, 0xE0074, 0xE007F)),

    // ๐ด๓?ง๓?ข๓?ท๓?ฌ๓?ณ๓?ฟ ๐ด๓?ง๓?ข๓?ท๓?ฌ๓?ณ๓?ฟ ๐ด๓?ง๓?ข๓?ท๓?ฌ๓?ณ๓?ฟ
    FLAG_WALES(intArrayOf(0x1F3F4, 0xE0067, 0xE0062, 0xE0077, 0xE006C, 0xE0073, 0xE007F));

    override fun toString() = String(intArray, 0, intArray.size)
}