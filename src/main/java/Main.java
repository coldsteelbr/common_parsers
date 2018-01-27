import ru.romanbrazhnikov.commonparsers.ICommonParser;
import ru.romanbrazhnikov.commonparsers.JsoupParser;

public class Main {
    private static String sPattern;
    private static String sSource = "<tbody>\n" +
            "\t\t\t\t\t\t\t\n" +
            "<tr class=\"hproduct  \" data-url=\"/rent/comm/city/universalnoe-pomeshchenie/raion_24_zaelcovskiy/101950135.html\">\n" +
            "\t<td class=\"align-center\">\n" +
            "\t\tуниверсальное помещение\t</td>\n" +
            "\t<td>\t</td>\n" +
            "\t<td class=\"pr20\">\n" +
            "\t\t<a class=\"advert-link fn value-title\" href=\"/rent/comm/city/universalnoe-pomeshchenie/raion_24_zaelcovskiy/101950135.html\" target=\"_blank\" title=\"Сдам универсальное помещение г. Новосибирск, Заельцовский р-он, ул. Жуковского, д. 108\">\n" +
            "\t\t\tг. Новосибирск, Заельцовский р-он, ул. Жуковского, д. 108\t\t</a>\n" +
            "\t\t\t</td>\n" +
            "\t<td>\n" +
            "\t\t<span>\n" +
            "\t\t\t<span title=\"общая площадь\">\n" +
            "\t\t\t\t10\t\t\t</span>\n" +
            "\t\t</span>\n" +
            "\t</td>\n" +
            "\n" +
            "\t\n" +
            "<td>\n" +
            "\t<span class=\"price\">4 000 руб.</span></td><td>месяц</td>\n" +
            "\t<td class=\"pr20\">\n" +
            "\t\t\t</td>\n" +
            "</tr>\n" +
            "\n" +
            "<tr class=\"hproduct  \" data-url=\"/rent/comm/city/office/raion_25_kalininskiy/101722738.html\">\n" +
            "\t<td class=\"align-center\">\n" +
            "\t\tофис\t</td>\n" +
            "\t<td>\t</td>\n" +
            "\t<td class=\"pr20\">\n" +
            "\t\t<a class=\"advert-link fn value-title\" href=\"/rent/comm/city/office/raion_25_kalininskiy/101722738.html\" target=\"_blank\" title=\"Сдам офис г. Новосибирск, Калининский р-он\">\n" +
            "\t\t\tг. Новосибирск, Калининский р-он\t\t</a>\n" +
            "\t\t\t</td>\n" +
            "\t<td>\n" +
            "\t\t<span>\n" +
            "\t\t\t<span title=\"общая площадь\">\n" +
            "\t\t\t\t30\t\t\t</span>\n" +
            "\t\t</span>\n" +
            "\t</td>\n" +
            "\n" +
            "\t\n" +
            "<td>\n" +
            "\t<span class=\"price\">4 500 руб.</span></td><td>месяц</td>\n" +
            "\t<td class=\"pr20\">\n" +
            "\t\tИП Назаров\t</td>\n" +
            "</tr>\n" +
            "\n" +
            "<tr class=\"hproduct  \" data-url=\"/rent/comm/city/office/raion_22_dzerzhinskiy/101975600.html\">\n" +
            "\t<td class=\"align-center\">\n" +
            "\t\tофис\t</td>\n" +
            "\t<td>\t\t\t<span class=\"icon-picture\"><img title=\"Сдам офис\" class=\"photo\" src=\"http://194.226.171.231:7500/dev1/0/004/807/0004807424.fid\" alt=\"Сдам офис\">\t\t\t</span>\t</td>\n" +
            "\t<td class=\"pr20\">\n" +
            "\t\t<a class=\"advert-link fn value-title\" href=\"/rent/comm/city/office/raion_22_dzerzhinskiy/101975600.html\" target=\"_blank\" title=\"Сдам офис г. Новосибирск, Дзержинский р-он, ул. Гоголя, д. 206/2\">\n" +
            "\t\t\tг. Новосибирск, Дзержинский р-он, ул. Гоголя, д. 206/2\t\t</a>\n" +
            "\t\t\t</td>\n" +
            "\t<td>\n" +
            "\t\t<span>\n" +
            "\t\t\t<span title=\"общая площадь\">\n" +
            "\t\t\t\t15\t\t\t</span>\n" +
            "\t\t</span>\n" +
            "\t</td>\n" +
            "\n" +
            "\t\n" +
            "<td>\n" +
            "\t<span class=\"price\">7 500 руб.</span></td><td>месяц</td>\n" +
            "\t<td class=\"pr20\">\n" +
            "\t\tПерспектива\t</td>\n" +
            "</tr>\n" +
            "\n" +
            "<tr class=\"hproduct  \" data-url=\"/rent/comm/city/sklad/raion_22_dzerzhinskiy/101949640.html\">\n" +
            "\t<td class=\"align-center\">\n" +
            "\t\tсклад\t</td>\n" +
            "\t<td>\t</td>\n" +
            "\t<td class=\"pr20\">\n" +
            "\t\t<a class=\"advert-link fn value-title\" href=\"/rent/comm/city/sklad/raion_22_dzerzhinskiy/101949640.html\" target=\"_blank\" title=\"Сдам склад г. Новосибирск, Дзержинский р-он, ул. Гоголя\">\n" +
            "\t\t\tг. Новосибирск, Дзержинский р-он, ул. Гоголя\t\t</a>\n" +
            "\t\t\t</td>\n" +
            "\t<td>\n" +
            "\t\t<span>\n" +
            "\t\t\t<span title=\"общая площадь\">\n" +
            "\t\t\t\t43\t\t\t</span>\n" +
            "\t\t</span>\n" +
            "\t</td>\n" +
            "\n" +
            "\t\n" +
            "<td>\n" +
            "\t<span class=\"price\">7 525 руб.</span></td><td>месяц</td>\n" +
            "\t<td class=\"pr20\">\n" +
            "\t\tПланета \t</td>\n" +
            "</tr>\n" +
            "\n" +
            "<tr class=\"hproduct  \" data-url=\"/rent/comm/city/office/raion_31_sovetskiy/101949874.html\">\n" +
            "\t<td class=\"align-center\">\n" +
            "\t\tофис\t</td>\n" +
            "\t<td>\t</td>\n" +
            "\t<td class=\"pr20\">\n" +
            "\t\t<a class=\"advert-link fn value-title\" href=\"/rent/comm/city/office/raion_31_sovetskiy/101949874.html\" target=\"_blank\" title=\"Сдам офис г. Новосибирск, Советский р-он, ул. Демакова\">\n" +
            "\t\t\tг. Новосибирск, Советский р-он, ул. Демакова\t\t</a>\n" +
            "\t\t\t</td>\n" +
            "\t<td>\n" +
            "\t\t<span>\n" +
            "\t\t\t<span title=\"общая площадь\">\n" +
            "\t\t\t\t21\t\t\t</span>\n" +
            "\t\t</span>\n" +
            "\t</td>\n" +
            "\n" +
            "\t\n" +
            "<td>\n" +
            "\t<span class=\"price\">9 000 руб.</span></td><td>месяц</td>\n" +
            "\t<td class=\"pr20\">\n" +
            "\t\tРесурсы Сибири\t</td>\n" +
            "</tr>\n" +
            "\n" +
            "<tr class=\"hproduct  \" data-url=\"/rent/comm/city/sklad/raion_22_dzerzhinskiy/101949908.html\">\n" +
            "\t<td class=\"align-center\">\n" +
            "\t\tсклад\t</td>\n" +
            "\t<td>\t</td>\n" +
            "\t<td class=\"pr20\">\n" +
            "\t\t<a class=\"advert-link fn value-title\" href=\"/rent/comm/city/sklad/raion_22_dzerzhinskiy/101949908.html\" target=\"_blank\" title=\"Сдам склад г. Новосибирск, Дзержинский р-он\">\n" +
            "\t\t\tг. Новосибирск, Дзержинский р-он\t\t</a>\n" +
            "\t\t\t</td>\n" +
            "\t<td>\n" +
            "\t\t<span>\n" +
            "\t\t\t<span title=\"общая площадь\">\n" +
            "\t\t\t\t160\t\t\t</span>\n" +
            "\t\t</span>\n" +
            "\t</td>\n" +
            "\n" +
            "\t\n" +
            "<td>\n" +
            "\t<span class=\"price\">10 000 руб.</span></td><td>месяц</td>\n" +
            "\t<td class=\"pr20\">\n" +
            "\t\tВячеслав  Александрович\t</td>\n" +
            "</tr>\n" +
            "\n" +
            "<tr class=\"hproduct  \" data-url=\"/rent/comm/city/office/raion_32_centralnyiy/101927589.html\">\n" +
            "\t<td class=\"align-center\">\n" +
            "\t\tофис\t</td>\n" +
            "\t<td>\t</td>\n" +
            "\t<td class=\"pr20\">\n" +
            "\t\t<a class=\"advert-link fn value-title\" href=\"/rent/comm/city/office/raion_32_centralnyiy/101927589.html\" target=\"_blank\" title=\"Сдам офис г. Новосибирск, Центральный р-он\">\n" +
            "\t\t\tг. Новосибирск, Центральный р-он\t\t</a>\n" +
            "\t\t\t</td>\n" +
            "\t<td>\n" +
            "\t\t<span>\n" +
            "\t\t\t<span title=\"общая площадь\">\n" +
            "\t\t\t\t12\t\t\t</span>\n" +
            "\t\t</span>\n" +
            "\t</td>\n" +
            "\n" +
            "\t\n" +
            "<td>\n" +
            "\t<span class=\"price\">11 000 руб.</span></td><td>месяц</td>\n" +
            "\t<td class=\"pr20\">\n" +
            "\t\t\t</td>\n" +
            "</tr>\n" +
            "\n" +
            "<tr class=\"hproduct  \" data-url=\"/rent/comm/city/torgovoe-pomeshchenie/raion_24_zaelcovskiy/101950136.html\">\n" +
            "\t<td class=\"align-center\">\n" +
            "\t\tторговое помещение\t</td>\n" +
            "\t<td>\t</td>\n" +
            "\t<td class=\"pr20\">\n" +
            "\t\t<a class=\"advert-link fn value-title\" href=\"/rent/comm/city/torgovoe-pomeshchenie/raion_24_zaelcovskiy/101950136.html\" target=\"_blank\" title=\"Сдам торговое помещение г. Новосибирск, Заельцовский р-он, ул. Жуковского, д. 108\">\n" +
            "\t\t\tг. Новосибирск, Заельцовский р-он, ул. Жуковского, д. 108\t\t</a>\n" +
            "\t\t\t</td>\n" +
            "\t<td>\n" +
            "\t\t<span>\n" +
            "\t\t\t<span title=\"общая площадь\">\n" +
            "\t\t\t\t30\t\t\t</span>\n" +
            "\t\t</span>\n" +
            "\t</td>\n" +
            "\n" +
            "\t\n" +
            "<td>\n" +
            "\t<span class=\"price\">12 000 руб.</span></td><td>месяц</td>\n" +
            "\t<td class=\"pr20\">\n" +
            "\t\t\t</td>\n" +
            "</tr>\n" +
            "\n" +
            "<tr class=\"hproduct  \" data-url=\"/rent/comm/city/office/raion_32_centralnyiy/101950221.html\">\n" +
            "\t<td class=\"align-center\">\n" +
            "\t\tофис\t</td>\n" +
            "\t<td>\t</td>\n" +
            "\t<td class=\"pr20\">\n" +
            "\t\t<a class=\"advert-link fn value-title\" href=\"/rent/comm/city/office/raion_32_centralnyiy/101950221.html\" target=\"_blank\" title=\"Сдам офис г. Новосибирск, Центральный р-он, ул. Серебренниковская, д. 19/1\">\n" +
            "\t\t\tг. Новосибирск, Центральный р-он, ул. Серебренниковская, д. 19/1\t\t</a>\n" +
            "\t\t\t</td>\n" +
            "\t<td>\n" +
            "\t\t<span>\n" +
            "\t\t\t<span title=\"общая площадь\">\n" +
            "\t\t\t\t17\t\t\t</span>\n" +
            "\t\t</span>\n" +
            "\t</td>\n" +
            "\n" +
            "\t\n" +
            "<td>\n" +
            "\t<span class=\"price\">12 040 руб.</span></td><td>месяц</td>\n" +
            "\t<td class=\"pr20\">\n" +
            "\t\tОльга\t</td>\n" +
            "</tr>\n" +
            "\n" +
            "<tr class=\"hproduct  \" data-url=\"/rent/comm/city/office/raion_32_centralnyiy/101646409.html\">\n" +
            "\t<td class=\"align-center\">\n" +
            "\t\tофис\t</td>\n" +
            "\t<td>\t</td>\n" +
            "\t<td class=\"pr20\">\n" +
            "\t\t<a class=\"advert-link fn value-title\" href=\"/rent/comm/city/office/raion_32_centralnyiy/101646409.html\" target=\"_blank\" title=\"Сдам офис г. Новосибирск, Центральный р-он, ул. Коммунистическая\">\n" +
            "\t\t\tг. Новосибирск, Центральный р-он, ул. Коммунистическая\t\t</a>\n" +
            "\t\t\t</td>\n" +
            "\t<td>\n" +
            "\t\t<span>\n" +
            "\t\t\t<span title=\"общая площадь\">\n" +
            "\t\t\t\t15\t\t\t</span>\n" +
            "\t\t</span>\n" +
            "\t</td>\n" +
            "\n" +
            "\t\n" +
            "<td>\n" +
            "\t<span class=\"price\">13 500 руб.</span></td><td>месяц</td>\n" +
            "\t<td class=\"pr20\">\n" +
            "\t\tКоммунистическая-35 ЗАО\t</td>\n" +
            "</tr>\n" +
            "\n" +
            "<tr class=\"hproduct  \" data-url=\"/rent/comm/city/office/raion_32_centralnyiy/101965508.html\">\n" +
            "\t<td class=\"align-center\">\n" +
            "\t\tофис\t</td>\n" +
            "\t<td>\t\t\t<span class=\"icon-picture\"><img title=\"Сдам офис\" class=\"photo\" src=\"http://194.226.171.231:7500/dev1/0/000/021/0000021646.fid\" alt=\"Сдам офис\">\t\t\t</span>\t</td>\n" +
            "\t<td class=\"pr20\">\n" +
            "\t\t<a class=\"advert-link fn value-title\" href=\"/rent/comm/city/office/raion_32_centralnyiy/101965508.html\" target=\"_blank\" title=\"Сдам офис г. Новосибирск, Центральный р-он,  Красный проспект, д. 29\">\n" +
            "\t\t\tг. Новосибирск, Центральный р-он,  Красный проспект, д. 29\t\t</a>\n" +
            "\t\t\t</td>\n" +
            "\t<td>\n" +
            "\t\t<span>\n" +
            "\t\t\t<span title=\"общая площадь\">\n" +
            "\t\t\t\t36\t\t\t</span>\n" +
            "\t\t</span>\n" +
            "\t</td>\n" +
            "\n" +
            "\t\n" +
            "<td>\n" +
            "\t<span class=\"price\">14 000 руб.</span></td><td></td>\n" +
            "\t<td class=\"pr20\">\n" +
            "\t\tЛевобережное\t</td>\n" +
            "</tr>\n" +
            "\n" +
            "<tr class=\"hproduct  \" data-url=\"/rent/comm/city/office/raion_30_pervomayskiy/101975740.html\">\n" +
            "\t<td class=\"align-center\">\n" +
            "\t\tофис\t</td>\n" +
            "\t<td>\t</td>\n" +
            "\t<td class=\"pr20\">\n" +
            "\t\t<a class=\"advert-link fn value-title\" href=\"/rent/comm/city/office/raion_30_pervomayskiy/101975740.html\" target=\"_blank\" title=\"Сдам офис г. Новосибирск, Первомайский р-он, ул. Заречная\">\n" +
            "\t\t\tг. Новосибирск, Первомайский р-он, ул. Заречная\t\t</a>\n" +
            "\t\t\t</td>\n" +
            "\t<td>\n" +
            "\t\t<span>\n" +
            "\t\t\t<span title=\"общая площадь\">\n" +
            "\t\t\t\t20\t\t\t</span>\n" +
            "\t\t</span>\n" +
            "\t</td>\n" +
            "\n" +
            "\t\n" +
            "<td>\n" +
            "\t<span class=\"price\">14 000 руб.</span></td><td>месяц</td>\n" +
            "\t<td class=\"pr20\">\n" +
            "\t\tФизическое лицо\t</td>\n" +
            "</tr>\n" +
            "\n" +
            "<tr class=\"hproduct  \" data-url=\"/rent/comm/city/office/raion_22_dzerzhinskiy/101975739.html\">\n" +
            "\t<td class=\"align-center\">\n" +
            "\t\tофис\t</td>\n" +
            "\t<td>\t</td>\n" +
            "\t<td class=\"pr20\">\n" +
            "\t\t<a class=\"advert-link fn value-title\" href=\"/rent/comm/city/office/raion_22_dzerzhinskiy/101975739.html\" target=\"_blank\" title=\"Сдам офис г. Новосибирск, Дзержинский р-он, ул. Гоголя\">\n" +
            "\t\t\tг. Новосибирск, Дзержинский р-он, ул. Гоголя\t\t</a>\n" +
            "\t\t\t</td>\n" +
            "\t<td>\n" +
            "\t\t<span>\n" +
            "\t\t\t<span title=\"общая площадь\">\n" +
            "\t\t\t\t25\t\t\t</span>\n" +
            "\t\t</span>\n" +
            "\t</td>\n" +
            "\n" +
            "\t\n" +
            "<td>\n" +
            "\t<span class=\"price\">14 750 руб.</span></td><td>месяц</td>\n" +
            "\t<td class=\"pr20\">\n" +
            "\t\tПланета \t</td>\n" +
            "</tr>\n" +
            "\n" +
            "<tr class=\"hproduct  \" data-url=\"/rent/comm/city/sklad/raion_22_dzerzhinskiy/101949639.html\">\n" +
            "\t<td class=\"align-center\">\n" +
            "\t\tсклад\t</td>\n" +
            "\t<td>\t</td>\n" +
            "\t<td class=\"pr20\">\n" +
            "\t\t<a class=\"advert-link fn value-title\" href=\"/rent/comm/city/sklad/raion_22_dzerzhinskiy/101949639.html\" target=\"_blank\" title=\"Сдам склад г. Новосибирск, Дзержинский р-он, ул. Гоголя\">\n" +
            "\t\t\tг. Новосибирск, Дзержинский р-он, ул. Гоголя\t\t</a>\n" +
            "\t\t\t</td>\n" +
            "\t<td>\n" +
            "\t\t<span>\n" +
            "\t\t\t<span title=\"общая площадь\">\n" +
            "\t\t\t\t88\t\t\t</span>\n" +
            "\t\t</span>\n" +
            "\t</td>\n" +
            "\n" +
            "\t\n" +
            "<td>\n" +
            "\t<span class=\"price\">15 400 руб.</span></td><td>месяц</td>\n" +
            "\t<td class=\"pr20\">\n" +
            "\t\tПланета \t</td>\n" +
            "</tr>\n" +
            "\n" +
            "<tr class=\"hproduct  \" data-url=\"/rent/comm/city/office/raion_22_dzerzhinskiy/101949913.html\">\n" +
            "\t<td class=\"align-center\">\n" +
            "\t\tофис\t</td>\n" +
            "\t<td>\t</td>\n" +
            "\t<td class=\"pr20\">\n" +
            "\t\t<a class=\"advert-link fn value-title\" href=\"/rent/comm/city/office/raion_22_dzerzhinskiy/101949913.html\" target=\"_blank\" title=\"Сдам офис г. Новосибирск, Дзержинский р-он\">\n" +
            "\t\t\tг. Новосибирск, Дзержинский р-он\t\t</a>\n" +
            "\t\t\t</td>\n" +
            "\t<td>\n" +
            "\t\t<span>\n" +
            "\t\t\t<span title=\"общая площадь\">\n" +
            "\t\t\t\t23\t\t\t</span>\n" +
            "\t\t</span>\n" +
            "\t</td>\n" +
            "\n" +
            "\t\n" +
            "<td>\n" +
            "\t<span class=\"price\">16 240 руб.</span></td><td>месяц</td>\n" +
            "\t<td class=\"pr20\">\n" +
            "\t\tСветлана\t</td>\n" +
            "</tr>\n" +
            "\n" +
            "<tr class=\"hproduct  \" data-url=\"/rent/comm/city/office/raion_32_centralnyiy/101927588.html\">\n" +
            "\t<td class=\"align-center\">\n" +
            "\t\tофис\t</td>\n" +
            "\t<td>\t</td>\n" +
            "\t<td class=\"pr20\">\n" +
            "\t\t<a class=\"advert-link fn value-title\" href=\"/rent/comm/city/office/raion_32_centralnyiy/101927588.html\" target=\"_blank\" title=\"Сдам офис г. Новосибирск, Центральный р-он\">\n" +
            "\t\t\tг. Новосибирск, Центральный р-он\t\t</a>\n" +
            "\t\t\t</td>\n" +
            "\t<td>\n" +
            "\t\t<span>\n" +
            "\t\t\t<span title=\"общая площадь\">\n" +
            "\t\t\t\t21\t\t\t</span>\n" +
            "\t\t</span>\n" +
            "\t</td>\n" +
            "\n" +
            "\t\n" +
            "<td>\n" +
            "\t<span class=\"price\">17 500 руб.</span></td><td>месяц</td>\n" +
            "\t<td class=\"pr20\">\n" +
            "\t\t\t</td>\n" +
            "</tr>\n" +
            "\n" +
            "<tr class=\"hproduct  \" data-url=\"/rent/comm/city/universalnoe-pomeshchenie/raion_32_centralnyiy/101927592.html\">\n" +
            "\t<td class=\"align-center\">\n" +
            "\t\tуниверсальное помещение\t</td>\n" +
            "\t<td>\t</td>\n" +
            "\t<td class=\"pr20\">\n" +
            "\t\t<a class=\"advert-link fn value-title\" href=\"/rent/comm/city/universalnoe-pomeshchenie/raion_32_centralnyiy/101927592.html\" target=\"_blank\" title=\"Сдам универсальное помещение г. Новосибирск, Центральный р-он\">\n" +
            "\t\t\tг. Новосибирск, Центральный р-он\t\t</a>\n" +
            "\t\t\t</td>\n" +
            "\t<td>\n" +
            "\t\t<span>\n" +
            "\t\t\t<span title=\"общая площадь\">\n" +
            "\t\t\t\t21\t\t\t</span>\n" +
            "\t\t</span>\n" +
            "\t</td>\n" +
            "\n" +
            "\t\n" +
            "<td>\n" +
            "\t<span class=\"price\">18 000 руб.</span></td><td>месяц</td>\n" +
            "\t<td class=\"pr20\">\n" +
            "\t\t\t</td>\n" +
            "</tr>\n" +
            "\n" +
            "<tr class=\"hproduct  \" data-url=\"/rent/comm/city/office/raion_32_centralnyiy/101927591.html\">\n" +
            "\t<td class=\"align-center\">\n" +
            "\t\tофис\t</td>\n" +
            "\t<td>\t</td>\n" +
            "\t<td class=\"pr20\">\n" +
            "\t\t<a class=\"advert-link fn value-title\" href=\"/rent/comm/city/office/raion_32_centralnyiy/101927591.html\" target=\"_blank\" title=\"Сдам офис г. Новосибирск, Центральный р-он\">\n" +
            "\t\t\tг. Новосибирск, Центральный р-он\t\t</a>\n" +
            "\t\t\t</td>\n" +
            "\t<td>\n" +
            "\t\t<span>\n" +
            "\t\t\t<span title=\"общая площадь\">\n" +
            "\t\t\t\t40\t\t\t</span>\n" +
            "\t\t</span>\n" +
            "\t</td>\n" +
            "\n" +
            "\t\n" +
            "<td>\n" +
            "\t<span class=\"price\">20 000 руб.</span></td><td>месяц</td>\n" +
            "\t<td class=\"pr20\">\n" +
            "\t\t\t</td>\n" +
            "</tr>\n" +
            "\n" +
            "<tr class=\"hproduct  \" data-url=\"/rent/comm/city/office/raion_32_centralnyiy/101955335.html\">\n" +
            "\t<td class=\"align-center\">\n" +
            "\t\tофис\t</td>\n" +
            "\t<td>\t\t\t<span class=\"icon-picture\"><img title=\"Сдам офис\" class=\"photo\" src=\"http://194.226.171.231:7500/dev1/0/000/021/0000021646.fid\" alt=\"Сдам офис\">\t\t\t</span>\t</td>\n" +
            "\t<td class=\"pr20\">\n" +
            "\t\t<a class=\"advert-link fn value-title\" href=\"/rent/comm/city/office/raion_32_centralnyiy/101955335.html\" target=\"_blank\" title=\"Сдам офис г. Новосибирск, Центральный р-он,  Красный проспект, д. 29\">\n" +
            "\t\t\tг. Новосибирск, Центральный р-он,  Красный проспект, д. 29\t\t</a>\n" +
            "\t\t\t</td>\n" +
            "\t<td>\n" +
            "\t\t<span>\n" +
            "\t\t\t<span title=\"общая площадь\">\n" +
            "\t\t\t\t37\t\t\t</span>\n" +
            "\t\t</span>\n" +
            "\t</td>\n" +
            "\n" +
            "\t\n" +
            "<td>\n" +
            "\t<span class=\"price\">22 000 руб.</span></td><td></td>\n" +
            "\t<td class=\"pr20\">\n" +
            "\t\tЛевобережное\t</td>\n" +
            "</tr>\n" +
            "\n" +
            "<tr class=\"hproduct  \" data-url=\"/rent/comm/city/universalnoe-pomeshchenie/raion_27_leninskiy/101974904.html\">\n" +
            "\t<td class=\"align-center\">\n" +
            "\t\tуниверсальное помещение\t</td>\n" +
            "\t<td>\t</td>\n" +
            "\t<td class=\"pr20\">\n" +
            "\t\t<a class=\"advert-link fn value-title\" href=\"/rent/comm/city/universalnoe-pomeshchenie/raion_27_leninskiy/101974904.html\" target=\"_blank\" title=\"Сдам универсальное помещение г. Новосибирск, Ленинский р-он, ул. Блюхера, д. 21\">\n" +
            "\t\t\tг. Новосибирск, Ленинский р-он, ул. Блюхера, д. 21\t\t</a>\n" +
            "\t\t\t</td>\n" +
            "\t<td>\n" +
            "\t\t<span>\n" +
            "\t\t\t<span title=\"общая площадь\">\n" +
            "\t\t\t\t27\t\t\t</span>\n" +
            "\t\t</span>\n" +
            "\t</td>\n" +
            "\n" +
            "\t\n" +
            "<td>\n" +
            "\t<span class=\"price\">25 000 руб.</span></td><td>месяц</td>\n" +
            "\t<td class=\"pr20\">\n" +
            "\t\t\t</td>\n" +
            "</tr>\n" +
            "\n" +
            "<tr class=\"hproduct  \" data-url=\"/rent/comm/city/office/raion_32_centralnyiy/101927590.html\">\n" +
            "\t<td class=\"align-center\">\n" +
            "\t\tофис\t</td>\n" +
            "\t<td>\t</td>\n" +
            "\t<td class=\"pr20\">\n" +
            "\t\t<a class=\"advert-link fn value-title\" href=\"/rent/comm/city/office/raion_32_centralnyiy/101927590.html\" target=\"_blank\" title=\"Сдам офис г. Новосибирск, Центральный р-он\">\n" +
            "\t\t\tг. Новосибирск, Центральный р-он\t\t</a>\n" +
            "\t\t\t</td>\n" +
            "\t<td>\n" +
            "\t\t<span>\n" +
            "\t\t\t<span title=\"общая площадь\">\n" +
            "\t\t\t\t36\t\t\t</span>\n" +
            "\t\t</span>\n" +
            "\t</td>\n" +
            "\n" +
            "\t\n" +
            "<td>\n" +
            "\t<span class=\"price\">25 000 руб.</span></td><td>месяц</td>\n" +
            "\t<td class=\"pr20\">\n" +
            "\t\t\t</td>\n" +
            "</tr>\n" +
            "\t\t\t\t\t\t\t</tbody>";


    public static void main(String[] args){
        ICommonParser parser = new JsoupParser();
        parser.setSource(sSource);
        parser.setPattern(sPattern);
        System.out.println("CommonParsers");
    }
}
