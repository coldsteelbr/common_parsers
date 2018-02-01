import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.reactivex.Single;
import ru.romanbrazhnikov.commonparsers.ICommonParser;
import ru.romanbrazhnikov.commonparsers.JsoupParser;
import ru.romanbrazhnikov.commonparsers.ParseResult;
import ru.romanbrazhnikov.commonparsers.XPathParser;

import java.util.*;

public class Main {
    private static String sSource = "<table class = \"pseudo\">\n" +
            "\t\t\t\t\t\t\t\n" +
            "<tr class=\"hproduct  \" data-url=\"/rent/comm/city/universalnoe-pomeshchenie/dist14_zaelcovskiy/jf84950135.php\">\n" +
            "\t<td class=\"align-center\">\n" +
            "\t\tуниверсальное помещение\t</td>\n" +
            "\t<td>\t</td>\n" +
            "\t<td class=\"cell_stylish\">\n" +
            "\t\t<a class=\"advert-link fn value-title\" href=\"/rent/comm/city/universalnoe-pomeshchenie/dist14_zaelcovskiy/jf84950135.php\" target=\"_blank\" title=\"Сдам универсальное помещение г. Городок, Заельцовский р-он, ул. Жуковского, д. 108\">\n" +
            "\t\t\tг. Городок, Заельцовский р-он, ул. Жуковского, д. 108\t\t</a>\n" +
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
            "\t<td class=\"cell_stylish\">\n" +
            "\t\t\t</td>\n" +
            "</tr>\n" +
            "\n" +
            "<tr class=\"hproduct  \" data-url=\"/rent/comm/city/retail/dist15_kalininskiy/jf84722738.php\">\n" +
            "\t<td class=\"align-center\">\n" +
            "\t\tофис\t</td>\n" +
            "\t<td>\t</td>\n" +
            "\t<td class=\"cell_stylish\">\n" +
            "\t\t<a class=\"advert-link fn value-title\" href=\"/rent/comm/city/retail/dist15_kalininskiy/jf84722738.php\" target=\"_blank\" title=\"Сдам офис г. Городок, Калининский р-он\">\n" +
            "\t\t\tг. Городок, Калининский р-он\t\t</a>\n" +
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
            "\t<td class=\"cell_stylish\">\n" +
            "\t\tИП Назаров\t</td>\n" +
            "</tr>\n" +
            "\n" +
            "<tr class=\"hproduct  \" data-url=\"/rent/comm/city/retail/dist12_dzerzhinskiy/jf8497560321.php\">\n" +
            "\t<td class=\"align-center\">\n" +
            "\t\tофис\t</td>\n" +
            "\t<td>\t\t\t<span class=\"icon-picture\"><img title=\"Сдам офис\" class=\"photo\" src=\"http://194.226.171.231:7500/dev1/0/004/807/0004807424.fid\" alt=\"Сдам офис\">\t\t\t</span>\t</td>\n" +
            "\t<td class=\"cell_stylish\">\n" +
            "\t\t<a class=\"advert-link fn value-title\" href=\"/rent/comm/city/retail/dist12_dzerzhinskiy/jf8497560321.php\" target=\"_blank\" title=\"Сдам офис г. Городок, Дзержинский р-он, ул. Гоголя, д. 206/2\">\n" +
            "\t\t\tг. Городок, Дзержинский р-он, ул. Гоголя, д. 206/2\t\t</a>\n" +
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
            "\t<td class=\"cell_stylish\">\n" +
            "\t\tПерспектива\t</td>\n" +
            "</tr>\n" +
            "\n" +
            "<tr class=\"hproduct  \" data-url=\"/rent/comm/city/sklad/dist12_dzerzhinskiy/jf8494964321.php\">\n" +
            "\t<td class=\"align-center\">\n" +
            "\t\tсклад\t</td>\n" +
            "\t<td>\t</td>\n" +
            "\t<td class=\"cell_stylish\">\n" +
            "\t\t<a class=\"advert-link fn value-title\" href=\"/rent/comm/city/sklad/dist12_dzerzhinskiy/jf8494964321.php\" target=\"_blank\" title=\"Сдам склад г. Городок, Дзержинский р-он, ул. Гоголя\">\n" +
            "\t\t\tг. Городок, Дзержинский р-он, ул. Гоголя\t\t</a>\n" +
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
            "\t<td class=\"cell_stylish\">\n" +
            "\t\tПланета \t</td>\n" +
            "</tr>\n" +
            "\n" +
            "<tr class=\"hproduct  \" data-url=\"/rent/comm/city/retail/dist41_sovetskiy/jf84949874.php\">\n" +
            "\t<td class=\"align-center\">\n" +
            "\t\tофис\t</td>\n" +
            "\t<td>\t</td>\n" +
            "\t<td class=\"cell_stylish\">\n" +
            "\t\t<a class=\"advert-link fn value-title\" href=\"/rent/comm/city/retail/dist41_sovetskiy/jf84949874.php\" target=\"_blank\" title=\"Сдам офис г. Городок, Советский р-он, ул. Демакова\">\n" +
            "\t\t\tг. Городок, Советский р-он, ул. Демакова\t\t</a>\n" +
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
            "\t<td class=\"cell_stylish\">\n" +
            "\t\tРесурсы Сибири\t</td>\n" +
            "</tr>\n" +
            "\n" +
            "<tr class=\"hproduct  \" data-url=\"/rent/comm/city/sklad/dist12_dzerzhinskiy/jf84949908.php\">\n" +
            "\t<td class=\"align-center\">\n" +
            "\t\tсклад\t</td>\n" +
            "\t<td>\t</td>\n" +
            "\t<td class=\"cell_stylish\">\n" +
            "\t\t<a class=\"advert-link fn value-title\" href=\"/rent/comm/city/sklad/dist12_dzerzhinskiy/jf84949908.php\" target=\"_blank\" title=\"Сдам склад г. Городок, Дзержинский р-он\">\n" +
            "\t\t\tг. Городок, Дзержинский р-он\t\t</a>\n" +
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
            "\t<td class=\"cell_stylish\">\n" +
            "\t\tВячеслав  Александрович\t</td>\n" +
            "</tr>\n" +
            "\n" +
            "<tr class=\"hproduct  \" data-url=\"/rent/comm/city/retail/dist42_centralnyiy/jf84927589.php\">\n" +
            "\t<td class=\"align-center\">\n" +
            "\t\tофис\t</td>\n" +
            "\t<td>\t</td>\n" +
            "\t<td class=\"cell_stylish\">\n" +
            "\t\t<a class=\"advert-link fn value-title\" href=\"/rent/comm/city/retail/dist42_centralnyiy/jf84927589.php\" target=\"_blank\" title=\"Сдам офис г. Городок, Центральный р-он\">\n" +
            "\t\t\tг. Городок, Центральный р-он\t\t</a>\n" +
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
            "\t<td class=\"cell_stylish\">\n" +
            "\t\t\t</td>\n" +
            "</tr>\n" +
            "\n" +
            "<tr class=\"hproduct  \" data-url=\"/rent/comm/city/torgovoe-pomeshchenie/dist14_zaelcovskiy/jf84950136.php\">\n" +
            "\t<td class=\"align-center\">\n" +
            "\t\tторговое помещение\t</td>\n" +
            "\t<td>\t</td>\n" +
            "\t<td class=\"cell_stylish\">\n" +
            "\t\t<a class=\"advert-link fn value-title\" href=\"/rent/comm/city/torgovoe-pomeshchenie/dist14_zaelcovskiy/jf84950136.php\" target=\"_blank\" title=\"Сдам торговое помещение г. Городок, Заельцовский р-он, ул. Жуковского, д. 108\">\n" +
            "\t\t\tг. Городок, Заельцовский р-он, ул. Жуковского, д. 108\t\t</a>\n" +
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
            "\t<td class=\"cell_stylish\">\n" +
            "\t\t\t</td>\n" +
            "</tr>\n" +
            "\n" +
            "<tr class=\"hproduct  \" data-url=\"/rent/comm/city/retail/dist42_centralnyiy/jf84950221.php\">\n" +
            "\t<td class=\"align-center\">\n" +
            "\t\tофис\t</td>\n" +
            "\t<td>\t</td>\n" +
            "\t<td class=\"cell_stylish\">\n" +
            "\t\t<a class=\"advert-link fn value-title\" href=\"/rent/comm/city/retail/dist42_centralnyiy/jf84950221.php\" target=\"_blank\" title=\"Сдам офис г. Городок, Центральный р-он, ул. Серебренниковская, д. 19/1\">\n" +
            "\t\t\tг. Городок, Центральный р-он, ул. Серебренниковская, д. 19/1\t\t</a>\n" +
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
            "\t<td class=\"cell_stylish\">\n" +
            "\t\tОльга\t</td>\n" +
            "</tr>\n" +
            "\n" +
            "<tr class=\"hproduct  \" data-url=\"/rent/comm/city/retail/dist42_centralnyiy/jf84646409.php\">\n" +
            "\t<td class=\"align-center\">\n" +
            "\t\tофис\t</td>\n" +
            "\t<td>\t</td>\n" +
            "\t<td class=\"cell_stylish\">\n" +
            "\t\t<a class=\"advert-link fn value-title\" href=\"/rent/comm/city/retail/dist42_centralnyiy/jf84646409.php\" target=\"_blank\" title=\"Сдам офис г. Городок, Центральный р-он, ул. Коммунистическая\">\n" +
            "\t\t\tг. Городок, Центральный р-он, ул. Коммунистическая\t\t</a>\n" +
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
            "\t<td class=\"cell_stylish\">\n" +
            "\t\tКоммунистическая-35 ЗАО\t</td>\n" +
            "</tr>\n" +
            "\n" +
            "<tr class=\"hproduct  \" data-url=\"/rent/comm/city/retail/dist42_centralnyiy/jf84965508.php\">\n" +
            "\t<td class=\"align-center\">\n" +
            "\t\tофис\t</td>\n" +
            "\t<td>\t\t\t<span class=\"icon-picture\"><img title=\"Сдам офис\" class=\"photo\" src=\"http://194.226.171.231:7500/dev1/0/000/021/0000021646.fid\" alt=\"Сдам офис\">\t\t\t</span>\t</td>\n" +
            "\t<td class=\"cell_stylish\">\n" +
            "\t\t<a class=\"advert-link fn value-title\" href=\"/rent/comm/city/retail/dist42_centralnyiy/jf84965508.php\" target=\"_blank\" title=\"Сдам офис г. Городок, Центральный р-он,  Красный проспект, д. 29\">\n" +
            "\t\t\tг. Городок, Центральный р-он,  Красный проспект, д. 29\t\t</a>\n" +
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
            "\t<td class=\"cell_stylish\">\n" +
            "\t\tЛевобережное\t</td>\n" +
            "</tr>\n" +
            "\n" +
            "<tr class=\"hproduct  \" data-url=\"/rent/comm/city/retail/dist40_pervomayskiy/jf8497574321.php\">\n" +
            "\t<td class=\"align-center\">\n" +
            "\t\tофис\t</td>\n" +
            "\t<td>\t</td>\n" +
            "\t<td class=\"cell_stylish\">\n" +
            "\t\t<a class=\"advert-link fn value-title\" href=\"/rent/comm/city/retail/dist40_pervomayskiy/jf8497574321.php\" target=\"_blank\" title=\"Сдам офис г. Городок, Первомайский р-он, ул. Заречная\">\n" +
            "\t\t\tг. Городок, Первомайский р-он, ул. Заречная\t\t</a>\n" +
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
            "\t<td class=\"cell_stylish\">\n" +
            "\t\tФизическое лицо\t</td>\n" +
            "</tr>\n" +
            "\n" +
            "<tr class=\"hproduct  \" data-url=\"/rent/comm/city/retail/dist12_dzerzhinskiy/jf84975739.php\">\n" +
            "\t<td class=\"align-center\">\n" +
            "\t\tофис\t</td>\n" +
            "\t<td>\t</td>\n" +
            "\t<td class=\"cell_stylish\">\n" +
            "\t\t<a class=\"advert-link fn value-title\" href=\"/rent/comm/city/retail/dist12_dzerzhinskiy/jf84975739.php\" target=\"_blank\" title=\"Сдам офис г. Городок, Дзержинский р-он, ул. Гоголя\">\n" +
            "\t\t\tг. Городок, Дзержинский р-он, ул. Гоголя\t\t</a>\n" +
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
            "\t<td class=\"cell_stylish\">\n" +
            "\t\tПланета \t</td>\n" +
            "</tr>\n" +
            "\n" +
            "<tr class=\"hproduct  \" data-url=\"/rent/comm/city/sklad/dist12_dzerzhinskiy/jf84949639.php\">\n" +
            "\t<td class=\"align-center\">\n" +
            "\t\tсклад\t</td>\n" +
            "\t<td>\t</td>\n" +
            "\t<td class=\"cell_stylish\">\n" +
            "\t\t<a class=\"advert-link fn value-title\" href=\"/rent/comm/city/sklad/dist12_dzerzhinskiy/jf84949639.php\" target=\"_blank\" title=\"Сдам склад г. Городок, Дзержинский р-он, ул. Гоголя\">\n" +
            "\t\t\tг. Городок, Дзержинский р-он, ул. Гоголя\t\t</a>\n" +
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
            "\t<td class=\"cell_stylish\">\n" +
            "\t\tПланета \t</td>\n" +
            "</tr>\n" +
            "\n" +
            "<tr class=\"hproduct  \" data-url=\"/rent/comm/city/retail/dist12_dzerzhinskiy/jf84949913.php\">\n" +
            "\t<td class=\"align-center\">\n" +
            "\t\tофис\t</td>\n" +
            "\t<td>\t</td>\n" +
            "\t<td class=\"cell_stylish\">\n" +
            "\t\t<a class=\"advert-link fn value-title\" href=\"/rent/comm/city/retail/dist12_dzerzhinskiy/jf84949913.php\" target=\"_blank\" title=\"Сдам офис г. Городок, Дзержинский р-он\">\n" +
            "\t\t\tг. Городок, Дзержинский р-он\t\t</a>\n" +
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
            "\t<td class=\"cell_stylish\">\n" +
            "\t\tСветлана\t</td>\n" +
            "</tr>\n" +
            "\n" +
            "<tr class=\"hproduct  \" data-url=\"/rent/comm/city/retail/dist42_centralnyiy/jf84927588.php\">\n" +
            "\t<td class=\"align-center\">\n" +
            "\t\tофис\t</td>\n" +
            "\t<td>\t</td>\n" +
            "\t<td class=\"cell_stylish\">\n" +
            "\t\t<a class=\"advert-link fn value-title\" href=\"/rent/comm/city/retail/dist42_centralnyiy/jf84927588.php\" target=\"_blank\" title=\"Сдам офис г. Городок, Центральный р-он\">\n" +
            "\t\t\tг. Городок, Центральный р-он\t\t</a>\n" +
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
            "\t<td class=\"cell_stylish\">\n" +
            "\t\t\t</td>\n" +
            "</tr>\n" +
            "\n" +
            "<tr class=\"hproduct  \" data-url=\"/rent/comm/city/universalnoe-pomeshchenie/dist42_centralnyiy/jf84927592.php\">\n" +
            "\t<td class=\"align-center\">\n" +
            "\t\tуниверсальное помещение\t</td>\n" +
            "\t<td>\t</td>\n" +
            "\t<td class=\"cell_stylish\">\n" +
            "\t\t<a class=\"advert-link fn value-title\" href=\"/rent/comm/city/universalnoe-pomeshchenie/dist42_centralnyiy/jf84927592.php\" target=\"_blank\" title=\"Сдам универсальное помещение г. Городок, Центральный р-он\">\n" +
            "\t\t\tг. Городок, Центральный р-он\t\t</a>\n" +
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
            "\t<td class=\"cell_stylish\">\n" +
            "\t\t\t</td>\n" +
            "</tr>\n" +
            "\n" +
            "<tr class=\"hproduct  \" data-url=\"/rent/comm/city/retail/dist42_centralnyiy/jf84927591.php\">\n" +
            "\t<td class=\"align-center\">\n" +
            "\t\tофис\t</td>\n" +
            "\t<td>\t</td>\n" +
            "\t<td class=\"cell_stylish\">\n" +
            "\t\t<a class=\"advert-link fn value-title\" href=\"/rent/comm/city/retail/dist42_centralnyiy/jf84927591.php\" target=\"_blank\" title=\"Сдам офис г. Городок, Центральный р-он\">\n" +
            "\t\t\tг. Городок, Центральный р-он\t\t</a>\n" +
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
            "\t<td class=\"cell_stylish\">\n" +
            "\t\t\t</td>\n" +
            "</tr>\n" +
            "\n" +
            "<tr class=\"hproduct  \" data-url=\"/rent/comm/city/retail/dist42_centralnyiy/jf84955335.php\">\n" +
            "\t<td class=\"align-center\">\n" +
            "\t\tофис\t</td>\n" +
            "\t<td>\t\t\t<span class=\"icon-picture\"><img title=\"Сдам офис\" class=\"photo\" src=\"http://194.226.171.231:7500/dev1/0/000/021/0000021646.fid\" alt=\"Сдам офис\">\t\t\t</span>\t</td>\n" +
            "\t<td class=\"cell_stylish\">\n" +
            "\t\t<a class=\"advert-link fn value-title\" href=\"/rent/comm/city/retail/dist42_centralnyiy/jf84955335.php\" target=\"_blank\" title=\"Сдам офис г. Городок, Центральный р-он,  Красный проспект, д. 29\">\n" +
            "\t\t\tг. Городок, Центральный р-он,  Красный проспект, д. 29\t\t</a>\n" +
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
            "\t<td class=\"cell_stylish\">\n" +
            "\t\tЛевобережное\t</td>\n" +
            "</tr>\n" +
            "\n" +
            "<tr class=\"hproduct  \" data-url=\"/rent/comm/city/universalnoe-pomeshchenie/dist17_leninskiy/jf84974904.php\">\n" +
            "\t<td class=\"align-center\">\n" +
            "\t\tуниверсальное помещение\t</td>\n" +
            "\t<td>\t</td>\n" +
            "\t<td class=\"cell_stylish\">\n" +
            "\t\t<a class=\"advert-link fn value-title\" href=\"/rent/comm/city/universalnoe-pomeshchenie/dist17_leninskiy/jf84974904.php\" target=\"_blank\" title=\"Сдам универсальное помещение г. Городок, Ленинский р-он, ул. Блюхера, д. 21\">\n" +
            "\t\t\tг. Городок, Ленинский р-он, ул. Блюхера, д. 21\t\t</a>\n" +
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
            "\t<td class=\"cell_stylish\">\n" +
            "\t\t\t</td>\n" +
            "</tr>\n" +
            "\n" +
            "<tr class=\"hproduct  \" data-url=\"/rent/comm/city/retail/dist42_centralnyiy/jf8492759321.php\">\n" +
            "\t<td class=\"align-center\">\n" +
            "\t\tофис\t</td>\n" +
            "\t<td>\t</td>\n" +
            "\t<td class=\"cell_stylish\">\n" +
            "\t\t<a class=\"advert-link fn value-title\" href=\"/rent/comm/city/retail/dist42_centralnyiy/jf8492759321.php\" target=\"_blank\" title=\"Сдам офис г. Городок, Центральный р-он\">\n" +
            "\t\t\tг. Городок, Центральный р-он\t\t</a>\n" +
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
            "\t<td class=\"cell_stylish\">\n" +
            "\t\t\t</td>\n" +
            "</tr>\n" +
            "\t\t\t\t\t\t\t</table>";

    private static String sPattern
            =
            "{\n" +
                "\"RowPattern\": \"//table[@class='pseudo']//tr\",\n" +
                "\"ColPatterns\": {\n" +
                    "\"ADDRESS\": \"td[@class='cell_stylish']/a\",\n" +
                    "\"SECOND\": \"td[@class='cell_stylish']/a/@href\"\n" +
                "}\n" +
            "}";

    public static void main(String[] args){
        System.out.println("CommonParsers");

        ICommonParser parser = new XPathParser();
        parser.setSource(sSource);
        parser.setPattern(sPattern);
        parser.setMatchNames(new HashSet<>(Arrays.asList("ADDRESS", "SECOND")));

        Map<String, String>  bind = new HashMap<>();
        bind.put("ADDRESS", "address");
        bind.put("SECOND", "second");
        parser.setBindings(bind);

        parser.parse().subscribe(parseResult -> {
            System.out.println("Nice:" + parseResult.getNiceLook() + "\n --- \n");
        },throwable -> {
            throwable.printStackTrace();
        });
    }
}
