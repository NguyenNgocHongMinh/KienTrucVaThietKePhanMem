package fit;

interface JsonService {
    void processJson(String json);
}

class XmlService {
    public void processXml(String xml) {
        System.out.println("Processing XML data: " + xml);
    }
}

// adapter
class JsonToXmlAdapter implements JsonService {

    private XmlService xmlService;

    public JsonToXmlAdapter(XmlService xmlService) {
        this.xmlService = xmlService;
    }

    @Override
    public void processJson(String json) {
        // JSON -> XML
        String xml = convertJsonToXml(json);

        System.out.println("JSON -> XML: " + xml);

        xmlService.processXml(xml);
    }

    private String convertJsonToXml(String json) {
        return "<data>" + json.replace("{", "")
                .replace("}", "")
                .replace(":", "=") + "</data>";
    }
}

// ====================== REVERSE ADAPTER ======================
interface XmlToJsonService {
    void processXml(String xml);
}

class JsonServiceImpl {
    public void processJson(String json) {
        System.out.println("Processing JSON data: " + json);
    }
}

class XmlToJsonAdapter implements XmlToJsonService {

    private JsonServiceImpl jsonService;

    public XmlToJsonAdapter(JsonServiceImpl jsonService) {
        this.jsonService = jsonService;
    }

    @Override
    public void processXml(String xml) {
        // -> JSON
        String json = convertXmlToJson(xml);

        System.out.println("XML -> JSON: " + json);

        jsonService.processJson(json);
    }

    private String convertXmlToJson(String xml) {
        return "{ " + xml.replace("<data>", "")
                .replace("</data>", "")
                .replace("=", ":") + " }";
    }
}


public class AdapterDemo3 {
    public static void main(String[] args) {
        String jsonData = "{name:John, age:25}";
        System.out.println(jsonData);
        XmlService xmlService = new XmlService();
        JsonService adapter = new JsonToXmlAdapter(xmlService);

        adapter.processJson(jsonData);

        String xmlData = "<data>name=Anna, age=22</data>";
        System.out.println(xmlData);

        JsonServiceImpl jsonService = new JsonServiceImpl();
        XmlToJsonService reverseAdapter = new XmlToJsonAdapter(jsonService);

        reverseAdapter.processXml(xmlData);
    }
}