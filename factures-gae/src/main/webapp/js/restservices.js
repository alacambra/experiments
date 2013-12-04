/**
 * Created by albert on 11/10/13.
 */

angular.module('httpInterceptor',[]).config(
        function($httpProvider) {
            $httpProvider.interceptors.push("requestInterceptor")
})
        .factory("requestInterceptor", function($q){
            return{
                'request': function(config){
                    return config
        }
    }
});



var RestServices = function(endpoint) {
    this.endpoint = endpoint;
    var restServicesSelf = this;
    var _buffer = {
        "budget":{
            "get":{}
        },
        "ic":{
            "get":{}
        },
        "pc":{
            "get":{}
        }
    };
    
    if (window.location.origin === "http://localhost") {
        _buffer["ic"]["get"][2013] = [{"id":4654945684946944,"cost":8858,"concept":null,"budgetId":4795683173302272,"tags":"real","date":1380960000000},{"id":4671403999625216,"cost":1873,"concept":null,"budgetId":4795683173302272,"tags":"nk","date":1375776000000},{"id":4691573166047232,"cost":3156,"concept":null,"budgetId":4795683173302272,"tags":"rewe","date":1383210000000},{"id":4701348511612928,"cost":1554,"concept":null,"budgetId":4795683173302272,"tags":"nk","date":1375084800000},{"id":4709487474638848,"cost":10446,"concept":null,"budgetId":4795683173302272,"tags":"real","date":1376121600000},{"id":4730468389879808,"cost":1815,"concept":null,"budgetId":4795683173302272,"tags":"nk","date":1380700800000},{"id":4749606764150784,"cost":5733,"concept":null,"budgetId":4795683173302272,"tags":"rewe","date":1375948800000},{"id":4753021263151104,"cost":2549,"concept":null,"budgetId":4795683173302272,"tags":"tegut","date":1375948800000},{"id":4773005745979392,"cost":3000,"concept":null,"budgetId":4795683173302272,"tags":"scoozi","date":1371542400000},{"id":4782742436839424,"cost":1033,"concept":null,"budgetId":4795683173302272,"tags":"nk","date":1374652800000},{"id":4790782615617536,"cost":700,"concept":null,"budgetId":4795683173302272,"tags":"nk","date":1366704000000},{"id":4807086311473152,"cost":1647,"concept":null,"budgetId":4795683173302272,"tags":"nk","date":1369814400000},{"id":4812141487980544,"cost":1800,"concept":null,"budgetId":4795683173302272,"tags":"nk","date":1367308800000},{"id":4827307017502720,"cost":357,"concept":null,"budgetId":4795683173302272,"tags":"obsthaus rebell","date":1372838400000},{"id":4832310654402560,"cost":1400,"concept":null,"budgetId":4795683173302272,"tags":"nk","date":1357030800000},{"id":4850224962994176,"cost":2500,"concept":null,"budgetId":4795683173302272,"tags":"nk","date":1367827200000},{"id":4857131270406144,"cost":9800,"concept":null,"budgetId":4795683173302272,"tags":"real","date":1367049600000},{"id":4871205878235136,"cost":9800,"concept":null,"budgetId":4795683173302272,"tags":"real","date":1367049600000},{"id":4890344252506112,"cost":4400,"concept":null,"budgetId":4795683173302272,"tags":"dm","date":1367827200000},{"id":4893758751506432,"cost":500,"concept":null,"budgetId":4795683173302272,"tags":"nk","date":1366876800000},{"id":4904019428376576,"cost":3451,"concept":null,"budgetId":4795683173302272,"tags":"rewe","date":1370332800000},{"id":5088561288183808,"cost":6000,"concept":null,"budgetId":4795683173302272,"tags":"cerveses papes","date":1365667200000},{"id":5204954901905408,"cost":1308,"concept":null,"budgetId":4795683173302272,"tags":"rewe","date":1370505600000},{"id":5217895638368256,"cost":1213,"concept":null,"budgetId":4795683173302272,"tags":"nk","date":1378108800000},{"id":5335955699400704,"cost":5114,"concept":null,"budgetId":4795683173302272,"tags":"rewe","date":1374912000000},{"id":5353732569038848,"cost":977,"concept":null,"budgetId":4795683173302272,"tags":"nk","date":1382342400000},{"id":5358633126723584,"cost":3000,"concept":null,"budgetId":4795683173302272,"tags":"rewe","date":1364889600000},{"id":5370036264894464,"cost":4000,"concept":null,"budgetId":4795683173302272,"tags":"city brautubl","date":1376553600000},{"id":5375091441401856,"cost":5200,"concept":null,"budgetId":4795683173302272,"tags":"delfino","date":1370937600000},{"id":5390256970924032,"cost":3324,"concept":null,"budgetId":4795683173302272,"tags":"tegut","date":1382428800000},{"id":5395260607823872,"cost":48,"concept":null,"budgetId":4795683173302272,"tags":"karagoz","date":1372665600000},{"id":5405035953389568,"cost":12700,"concept":null,"budgetId":4795683173302272,"tags":"real","date":1366444800000},{"id":5413174916415488,"cost":1765,"concept":null,"budgetId":4795683173302272,"tags":"nk","date":1378540800000},{"id":5420081223827456,"cost":1924,"concept":null,"budgetId":4795683173302272,"tags":"nk","date":1381737600000},{"id":5434155831656448,"cost":3500,"concept":null,"budgetId":4795683173302272,"tags":"scozzi","date":1371628800000},{"id":5453294205927424,"cost":9252,"concept":null,"budgetId":4795683173302272,"tags":"real","date":1373702400000},{"id":5456708704927744,"cost":1216,"concept":null,"budgetId":4795683173302272,"tags":"obsthaus rebell","date":1373529600000},{"id":5466969381797888,"cost":1087,"concept":null,"budgetId":4795683173302272,"tags":"","date":1381132800000},{"id":5476693187756032,"cost":600,"concept":null,"budgetId":4795683173302272,"tags":"nk","date":1366272000000},{"id":5486429878616064,"cost":2451,"concept":null,"budgetId":4795683173302272,"tags":"tegut","date":1373529600000},{"id":5780845591789568,"cost":1307,"concept":null,"budgetId":4795683173302272,"tags":"nk","date":1382083200000},{"id":5797303906467840,"cost":1983,"concept":null,"budgetId":4795683173302272,"tags":"nk","date":1375171200000},{"id":5827248418455552,"cost":2130,"concept":null,"budgetId":4795683173302272,"tags":"netto","date":1374220800000},{"id":5898905652822016,"cost":2503,"concept":null,"budgetId":4795683173302272,"tags":"molina","date":1371024000000},{"id":5916682522460160,"cost":2503,"concept":null,"budgetId":4795683173302272,"tags":"molina","date":1371024000000},{"id":5921583080144896,"cost":2506,"concept":null,"budgetId":4795683173302272,"tags":"rewe","date":1369641600000},{"id":5932986218315776,"cost":1296,"concept":null,"budgetId":4795683173302272,"tags":"nk","date":1373011200000},{"id":5938041394823168,"cost":4700,"concept":null,"budgetId":4795683173302272,"tags":"rewe","date":1367308800000},{"id":5953206924345344,"cost":2200,"concept":null,"budgetId":4795683173302272,"tags":"carn bbq","date":1365753600000},{"id":5958210561245184,"cost":800,"concept":null,"budgetId":4795683173302272,"tags":"nk","date":1367568000000},{"id":5976124869836800,"cost":9202,"concept":null,"budgetId":4795683173302272,"tags":"real","date":1372492800000},{"id":5983031177248768,"cost":1118,"concept":null,"budgetId":4795683173302272,"tags":"nk","date":1372752000000},{"id":5997105785077760,"cost":1500,"concept":null,"budgetId":4795683173302272,"tags":"euro supermarkt","date":1366790400000},{"id":6016244159348736,"cost":2031,"concept":null,"budgetId":4795683173302272,"tags":"nk","date":1370246400000},{"id":6019658658349056,"cost":779,"concept":null,"budgetId":4795683173302272,"tags":"tegut","date":1370419200000},{"id":6029919335219200,"cost":1128,"concept":null,"budgetId":4795683173302272,"tags":"nk","date":1371456000000},{"id":6049379832037376,"cost":16000,"concept":null,"budgetId":4795683173302272,"tags":"real","date":1365667200000},{"id":6198157499170816,"cost":12800,"concept":null,"budgetId":4795683173302272,"tags":"real","date":1368259200000},{"id":6219516371533824,"cost":500,"concept":null,"budgetId":4795683173302272,"tags":"nk","date":1366012800000},{"id":6297719136059392,"cost":1163,"concept":null,"budgetId":4795683173302272,"tags":"rewe","date":1369382400000},{"id":6311394311929856,"cost":1600,"concept":null,"budgetId":4795683173302272,"tags":"nk","date":1366790400000},{"id":6461855606243328,"cost":1733,"concept":null,"budgetId":4795683173302272,"tags":"nk","date":1377244800000},{"id":6479632475881472,"cost":845,"concept":null,"budgetId":4795683173302272,"tags":"nk","date":1376467200000},{"id":6484533033566208,"cost":5757,"concept":null,"budgetId":4795683173302272,"tags":"real","date":1373097600000},{"id":6495936171737088,"cost":15394,"concept":null,"budgetId":4795683173302272,"tags":"real","date":1381910400000},{"id":6500991348244480,"cost":8100,"concept":null,"budgetId":4795683173302272,"tags":"molinas","date":1373702400000},{"id":6516156877766656,"cost":2467,"concept":null,"budgetId":4795683173302272,"tags":"nk","date":1376812800000},{"id":6521160514666496,"cost":762,"concept":null,"budgetId":4795683173302272,"tags":"nk","date":1373270400000},{"id":6530935860232192,"cost":2797,"concept":null,"budgetId":4795683173302272,"tags":"rewe","date":1372924800000},{"id":6539074823258112,"cost":889,"concept":null,"budgetId":4795683173302272,"tags":"alnatura","date":1375689600000},{"id":6545981130670080,"cost":2444,"concept":null,"budgetId":4795683173302272,"tags":"nk","date":1375344000000},{"id":6560055738499072,"cost":2015,"concept":null,"budgetId":4795683173302272,"tags":"rewe","date":1380528000000},{"id":6579194112770048,"cost":6078,"concept":null,"budgetId":4795683173302272,"tags":"real","date":1377331200000},{"id":6582608611770368,"cost":14828,"concept":null,"budgetId":4795683173302272,"tags":"real","date":1375516800000},{"id":6592869288640512,"cost":2751,"concept":null,"budgetId":4795683173302272,"tags":"nk","date":1374480000000},{"id":6602593094598656,"cost":1500,"concept":null,"budgetId":4795683173302272,"tags":"nk","date":1365408000000},{"id":6612329785458688,"cost":648,"concept":null,"budgetId":4795683173302272,"tags":"saltxixa","date":1382864400000},{"id":4990962451349504,"cost":4317,"concept":null,"budgetId":4952878976335872,"tags":"benzina","date":1382864400000},{"id":5680446436278272,"cost":330,"concept":null,"budgetId":4952878976335872,"tags":"rmv","date":1377158400000},{"id":6338894987526144,"cost":3474,"concept":null,"budgetId":4952878976335872,"tags":"benzina","date":1376726400000},{"id":6452131800285184,"cost":22400,"concept":null,"budgetId":4952878976335872,"tags":"rodes noves, canvi, llum matricula","date":1367049600000},{"id":6753067273814016,"cost":774,"concept":null,"budgetId":4952878976335872,"tags":"benzina","date":1380355200000},{"id":4666348823117824,"cost":1490,"concept":null,"budgetId":5054480722690048,"tags":"cinemaxx","date":1374998400000},{"id":4686569529147392,"cost":2194,"concept":null,"budgetId":5054480722690048,"tags":"amazon","date":1380873600000},{"id":4716393782050816,"cost":3000,"concept":null,"budgetId":5054480722690048,"tags":"pelicules ironman/alien","date":1370160000000},{"id":5264298465034240,"cost":1900,"concept":null,"budgetId":5054480722690048,"tags":"pelicules dvd","date":1377072000000},{"id":5293418343301120,"cost":3600,"concept":null,"budgetId":5054480722690048,"tags":"cine","date":1376726400000},{"id":5792248729960448,"cost":1200,"concept":null,"budgetId":5054480722690048,"tags":"cine","date":1376985600000},{"id":5842293688893440,"cost":8892,"concept":null,"budgetId":5054480722690048,"tags":"mediamarkt","date":1377331200000},{"id":5856368296722432,"cost":3500,"concept":null,"budgetId":5054480722690048,"tags":"festa amb miguel al krone","date":1365235200000},{"id":6419318250143744,"cost":310,"concept":null,"budgetId":5054480722690048,"tags":"vacances sion","date":1382428800000},{"id":4763281940021248,"cost":52000,"concept":null,"budgetId":5229298776539136,"tags":"Hochzeits kleid","date":1370073600000},{"id":4982823488323584,"cost":400,"concept":null,"budgetId":5229298776539136,"tags":"thalia","date":1367827200000},{"id":5011943366590464,"cost":1200,"concept":null,"budgetId":5229298776539136,"tags":"textilpflege nord","date":1366963200000},{"id":5234353953046528,"cost":5000,"concept":null,"budgetId":5229298776539136,"tags":"tkmax","date":1377417600000},{"id":5279343735472128,"cost":6900,"concept":null,"budgetId":5229298776539136,"tags":"armilla casament","date":1377244800000},{"id":5345692390260736,"cost":4999,"concept":null,"budgetId":5229298776539136,"tags":"esprit","date":1381737600000},{"id":5908642343682048,"cost":1200,"concept":null,"budgetId":5229298776539136,"tags":"mitjons casament","date":1365753600000},{"id":6108723395166208,"cost":1234,"concept":null,"budgetId":5229298776539136,"tags":"kaufhof","date":1380960000000},{"id":6137843273433088,"cost":26985,"concept":null,"budgetId":5229298776539136,"tags":"traje casament","date":1376121600000},{"id":6343795545210880,"cost":9000,"concept":null,"budgetId":5229298776539136,"tags":"kaufhof","date":1366272000000},{"id":6355198683381760,"cost":1500,"concept":null,"budgetId":5229298776539136,"tags":"kaufhof","date":1366876800000},{"id":6390198371876864,"cost":10000,"concept":null,"budgetId":5229298776539136,"tags":"kaufhof","date":1366272000000},{"id":6405243642314752,"cost":14150,"concept":null,"budgetId":5229298776539136,"tags":"maquillatge casament","date":1377244800000},{"id":4842085999968256,"cost":897,"concept":null,"budgetId":5640108103434240,"tags":"gel","date":1375516800000},{"id":4913743234334720,"cost":1410,"concept":null,"budgetId":5640108103434240,"tags":"dm","date":1370592000000},{"id":4923479925194752,"cost":880,"concept":null,"budgetId":5640108103434240,"tags":"dm","date":1381392000000},{"id":5072257592328192,"cost":2500,"concept":null,"budgetId":5640108103434240,"tags":"perruqueria","date":1377158400000},{"id":5077158150012928,"cost":1300,"concept":null,"budgetId":5640108103434240,"tags":"tchibo","date":1367827200000},{"id":5093616464691200,"cost":1200,"concept":null,"budgetId":5640108103434240,"tags":"tintoreria","date":1365235200000},{"id":5108781994213376,"cost":1143,"concept":null,"budgetId":5640108103434240,"tags":"nk","date":1378195200000},{"id":5113785631113216,"cost":15900,"concept":null,"budgetId":5640108103434240,"tags":"apirador","date":1377504000000},{"id":5123560976678912,"cost":1300,"concept":null,"budgetId":5640108103434240,"tags":"dm","date":1366876800000},{"id":5131699939704832,"cost":2635,"concept":null,"budgetId":5640108103434240,"tags":"dm","date":1369987200000},{"id":5138606247116800,"cost":3085,"concept":null,"budgetId":5640108103434240,"tags":"tchibo","date":1383037200000},{"id":5152680854945792,"cost":770,"concept":null,"budgetId":5640108103434240,"tags":"dm","date":1373270400000},{"id":5171819229216768,"cost":2900,"concept":null,"budgetId":5640108103434240,"tags":"dm","date":1363338000000},{"id":5175233728217088,"cost":1300,"concept":null,"budgetId":5640108103434240,"tags":"anzug reinigung","date":1366012800000},{"id":5185494405087232,"cost":4100,"concept":null,"budgetId":5640108103434240,"tags":"farmacia","date":1366963200000},{"id":5635207545749504,"cost":5424,"concept":null,"budgetId":5640108103434240,"tags":"baumarkt","date":1370073600000},{"id":5651511241605120,"cost":2100,"concept":null,"budgetId":5640108103434240,"tags":"dm","date":1371715200000},{"id":5656566418112512,"cost":1334,"concept":null,"budgetId":5640108103434240,"tags":"heimweh","date":1373011200000},{"id":5671731947634688,"cost":1900,"concept":null,"budgetId":5640108103434240,"tags":"peluqueria","date":1365235200000},{"id":5676735584534528,"cost":11604,"concept":null,"budgetId":5640108103434240,"tags":"real","date":1370073600000},{"id":5694649893126144,"cost":5985,"concept":null,"budgetId":5640108103434240,"tags":"hugendubel","date":1374652800000},{"id":5701556200538112,"cost":18800,"concept":null,"budgetId":5640108103434240,"tags":"Molina","date":1368086400000},{"id":5715630808367104,"cost":1000,"concept":null,"budgetId":5640108103434240,"tags":"post","date":1366963200000},{"id":5734769182638080,"cost":1390,"concept":null,"budgetId":5640108103434240,"tags":"dm","date":1373011200000},{"id":5738183681638400,"cost":948,"concept":null,"budgetId":5640108103434240,"tags":"tkmaxx","date":1370592000000},{"id":5748444358508544,"cost":2255,"concept":null,"budgetId":5640108103434240,"tags":"dm","date":1371196800000},{"id":5767904855326720,"cost":1500,"concept":null,"budgetId":5640108103434240,"tags":"dm","date":1365840000000},{"id":5967985906810880,"cost":755,"concept":null,"budgetId":5640108103434240,"tags":"pissarra","date":1377072000000},{"id":6039643141177344,"cost":2645,"concept":null,"budgetId":5640108103434240,"tags":"dm","date":1377158400000},{"id":6203058056855552,"cost":1100,"concept":null,"budgetId":5640108103434240,"tags":"baumarkt","date":1371369600000},{"id":6214461195026432,"cost":2756,"concept":null,"budgetId":5640108103434240,"tags":"farmacia","date":1368777600000},{"id":6234681901056000,"cost":1190,"concept":null,"budgetId":5640108103434240,"tags":"nk","date":1377244800000},{"id":6239685537955840,"cost":2490,"concept":null,"budgetId":5640108103434240,"tags":"dm","date":1376640000000},{"id":6249460883521536,"cost":1300,"concept":null,"budgetId":5640108103434240,"tags":"haufhof","date":1371196800000},{"id":6257599846547456,"cost":2985,"concept":null,"budgetId":5640108103434240,"tags":"dm","date":1382950800000},{"id":6264506153959424,"cost":370,"concept":null,"budgetId":5640108103434240,"tags":"farmacia","date":1375948800000},{"id":6278580761788416,"cost":9701,"concept":null,"budgetId":5640108103434240,"tags":"reparacio jura","date":1380960000000},{"id":6301133635059712,"cost":1440,"concept":null,"budgetId":5640108103434240,"tags":"dm","date":1377244800000},{"id":6321118117888000,"cost":1200,"concept":null,"budgetId":5640108103434240,"tags":"copia claus","date":1365235200000},{"id":6330854808748032,"cost":3750,"concept":null,"budgetId":5640108103434240,"tags":"dm","date":1377849600000},{"id":4554546529435648,"cost":1256,"concept":null,"budgetId":6180380629532672,"tags":"obsthaus","date":1381478400000},{"id":4650045127262208,"cost":2900,"concept":null,"budgetId":6180380629532672,"tags":"scoozi","date":1367740800000},{"id":4936420661657600,"cost":5980,"concept":null,"budgetId":6180380629532672,"tags":"delfino","date":1369382400000},{"id":4947823799828480,"cost":5200,"concept":null,"budgetId":6180380629532672,"tags":"braustubl","date":1366531200000},{"id":4997868758761472,"cost":4100,"concept":null,"budgetId":6180380629532672,"tags":"braustubl","date":1365062400000},{"id":5064217413550080,"cost":1454,"concept":null,"budgetId":6180380629532672,"tags":"nk","date":1369296000000},{"id":5212995080683520,"cost":3500,"concept":null,"budgetId":6180380629532672,"tags":"mosch mosch","date":1375862400000},{"id":5249519482568704,"cost":3570,"concept":null,"budgetId":6180380629532672,"tags":"dimitris","date":1383296400000},{"id":5254523119468544,"cost":3310,"concept":null,"budgetId":6180380629532672,"tags":"wokman","date":1383210000000},{"id":5272437428060160,"cost":68,"concept":null,"budgetId":6180380629532672,"tags":"linzerstube","date":1373529600000},{"id":5312556717572096,"cost":12470,"concept":null,"budgetId":6180380629532672,"tags":"NEU KONG","date":1370073600000},{"id":5315971216572416,"cost":2830,"concept":null,"budgetId":6180380629532672,"tags":"pizzeria","date":1382601600000},{"id":5326231893442560,"cost":3990,"concept":null,"budgetId":6180380629532672,"tags":"delfino","date":1383210000000},{"id":5499370615078912,"cost":2400,"concept":null,"budgetId":6180380629532672,"tags":"grohe","date":1373616000000},{"id":5510773753249792,"cost":3360,"concept":null,"budgetId":6180380629532672,"tags":"havana","date":1365321600000},{"id":5545773441744896,"cost":5100,"concept":null,"budgetId":6180380629532672,"tags":"braustubl","date":1374566400000},{"id":5560818712182784,"cost":2500,"concept":null,"budgetId":6180380629532672,"tags":"Scoozi","date":1370592000000},{"id":5574893320011776,"cost":890,"concept":null,"budgetId":6180380629532672,"tags":"tee gschwender","date":1365840000000},{"id":5617430676111360,"cost":1692,"concept":null,"budgetId":6180380629532672,"tags":"nk","date":1369123200000},{"id":5627167366971392,"cost":1850,"concept":null,"budgetId":6180380629532672,"tags":"zendo","date":1378195200000},{"id":5775945034104832,"cost":5200,"concept":null,"budgetId":6180380629532672,"tags":"delfino","date":1370937600000},{"id":5812469435990016,"cost":26,"concept":null,"budgetId":6180380629532672,"tags":"city braustub'l","date":1369209600000},{"id":5817473072889856,"cost":4000,"concept":null,"budgetId":6180380629532672,"tags":"scoozi","date":1364803200000},{"id":5835387381481472,"cost":2000,"concept":null,"budgetId":6180380629532672,"tags":"wokman","date":1364803200000},{"id":5875506670993408,"cost":2000,"concept":null,"budgetId":6180380629532672,"tags":"wokman","date":1369036800000},{"id":5878921169993728,"cost":5200,"concept":null,"budgetId":6180380629532672,"tags":"delfino","date":1373011200000},{"id":5889181846863872,"cost":100,"concept":null,"budgetId":6180380629532672,"tags":"removme","date":1364803200000},{"id":6062320568500224,"cost":1670,"concept":null,"budgetId":6180380629532672,"tags":"scoozi","date":1369814400000},{"id":6073723706671104,"cost":3200,"concept":null,"budgetId":6180380629532672,"tags":"scoozi","date":1382864400000},{"id":6123768665604096,"cost":5990,"concept":null,"budgetId":6180380629532672,"tags":"sitte","date":1377158400000},{"id":6190117320392704,"cost":3600,"concept":null,"budgetId":6180380629532672,"tags":"scoozi","date":1374048000000},{"id":6360253859889152,"cost":4700,"concept":null,"budgetId":6180380629532672,"tags":"linzerstube","date":1366185600000},{"id":6375419389411328,"cost":5250,"concept":null,"budgetId":6180380629532672,"tags":"delfino","date":1376467200000},{"id":6380423026311168,"cost":4600,"concept":null,"budgetId":6180380629532672,"tags":"cubana","date":1374220800000},{"id":6398337334902784,"cost":3800,"concept":null,"budgetId":6180380629532672,"tags":"karagoez","date":1375171200000},{"id":6438456624414720,"cost":4030,"concept":null,"budgetId":6180380629532672,"tags":"terras del rey","date":1376035200000},{"id":6471592297103360,"cost":6000,"concept":null,"budgetId":6180380629532672,"tags":"karagoez","date":1365580800000},{"id":6625270521921536,"cost":5100,"concept":null,"budgetId":6180380629532672,"tags":"braustubl","date":1374566400000},{"id":6671673348587520,"cost":4630,"concept":null,"budgetId":6180380629532672,"tags":"kartoffelhaus","date":1377676800000},{"id":6700793226854400,"cost":4300,"concept":null,"budgetId":6180380629532672,"tags":"grohe","date":1367222400000},{"id":6743330582953984,"cost":1550,"concept":null,"budgetId":6180380629532672,"tags":"scoozi","date":1375948800000}];
        _buffer["budget"]["get"][2013] = [{"id":4795683173302272,"name":"Menjar","year":2013,"amount":500000,"total":null},{"id":4952878976335872,"name":"cotxe","year":2013,"amount":60000,"total":null},{"id":5054480722690048,"name":"Oci","year":2013,"amount":100000,"total":null},{"id":5229298776539136,"name":"roba","year":2013,"amount":50000,"total":null},{"id":5640108103434240,"name":"Intendencia","year":2013,"amount":10000,"total":null},{"id":6180380629532672,"name":"Restaurant","year":2013,"amount":180000,"total":null}];
    }
    
    return {
        "bufferRestServices":function($http) {
            var buffer = _buffer["budget"];
            
            return {
                "getBudget":function (budgetId) {
                    a = 0;
                },
                "getBudgetsForYear":function (year, callback) {
                    
                    if (buffer["get"].hasOwnProperty(year)) {
                        callback(buffer["get"][year]);
                        return;
                    }
                    
                    $http.get(endpoint + '/budget/year/' + year).
                            success(function(data) {
                                buffer["get"][year] = data;
                        callback(data);
                    }).error(function(data, status, headers, config) {
                        
                        if(status===401){
                            window.location = data;
                        }
                    });
                },
                
                "getAllBudgets":function () {},
                
                "updateBudget":function (budget) {},
                
                "addBudget":function (bg) {
                    buffer["get"] = {};
                    return $http.put(endpoint + '/budget/', bg);
                }
            };
        },
        "individualCostService":function($http) {
            
            var buffer = _buffer["ic"];
            
            return {
                "individualCost":function getIndividualCost(budgetId, invoiceId){
                },
                
                "getIndividualCosts":function getIndividualCosts(year, callback){
                    
                    if (buffer["get"].hasOwnProperty(year)) {
                        callback(buffer["get"][year]);
                        return;
                    }
                    
                    $http.get(endpoint + '/individualcost/year/' + year).
                            success(function(data) {
                                
                                
                                buffer["get"][year] = data;
                                callback(data);
                    }).error(function(data, status, headers, config) {
                        
                        if(status===401){
                            window.location = data;
                        }
                    });
                },
                "saveIndividualCost":function saveIndividualCost(cost){
                    buffer["get"] = {}
                    return $http.put(endpoint + '/individualcost/', cost);
                },
                
                "updateIndividualCost":function updateIndividualCost(costId, invoice){
                    
                },
                
                "deleteIndividualCost":function deleteIndividualCost(budgetId, costId){
                    
                }
            }
        },
        "periodicCostService":function($http) {
            
            var buffer = _buffer["pc"];
            
            return {
                "getPeriodicCost":function getPeriodicCost(budgetId, costId){
                },
                
                "getAllPeriodicCosts":function getAllPeriodicCosts(year, callback){
                    
                    if (buffer["get"].hasOwnProperty(year)) {
                        callback(buffer["get"][year]);
                        return;
                    }
                    
                    $http.get(endpoint + '/periodiccost/year/' + year).
                            success(function(data) {
                                buffer["get"][year] = data;
                        callback(data)
                    }).error(function(data, status, headers, config) {
                        
                        if(status===401){
                            window.location = data;
                        }
                    });
                },
                "savePeriodicCost":function savePeriodicCost(cost){
                    buffer["get"] = {}
                    return $http.put(endpoint + '/periodiccost/', cost);
                },
                
                "updateCostEntry":function updateCostEntry(budgetId, costId, entryId, invoice){
                    
                },
                
                "updatePeriodicCost":function updatePeriodicCost(budgetId, costId, entryId, invoice){
                    
                },
                
                "deleteCostEntry":function deleteCostEntry(budgetId, costId, entryId){
                    
                },
                
                "deletePeriodicCost":function deletePeriodicCost(budgetId, costId, entryId){
                    
                }
            }
        }
    }
};