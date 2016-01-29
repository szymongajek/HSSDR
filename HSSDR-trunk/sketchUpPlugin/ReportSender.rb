
require 'SendToServer.rb'
require 'DCAttrReporter.rb'
## Plugin korzystajac z DCAttrReporter wysyla na serwer HSSDR(def socketa w SendToServer) raport DC Attr. 
## wysylane sa obiekty zaznaczone jako Dynamic Components
## to podejscie sie zdezauktualizowalo - SketchUp wspiera oznaczanie i export IFC
class ReportSender
  
  def sendReportToHSSDR
    attr_reporter = DCAttrReporter.new
    
    reportString = attr_reporter.get_attributes_report(Sketchup.active_model.entities)
    
    response = sendData(reportString)
    
    SKETCHUP_CONSOLE.write(response+"\n")
     
   end
end

if( not $ReportSender_loaded )

  reportSender = ReportSender.new

  # dodaje menu Plugins>HSSDR>Send to HSSDR
  plugins_menu = UI.menu "Plugins"
  TextTest_menu = plugins_menu.add_submenu("HSSDR")
  TextTest_menu.add_item("Send to HSSDR") {
    reportSender.sendReportToHSSDR()
  }

  $ReportSender_loaded = true

end
