#include "SoftwareSerial.h"

String receivedata;
String ssid ="";//ssid
String password="";//password

SoftwareSerial esp(4, 5);// RX, TX
String data;
String server = "120.127.14.91"; // www.example.com
String uri = "yourURI";// our example is /esppost.php


void setup()
{
  esp.begin(9600);
  Serial.begin(9600);
  reset();
  changemode();
  connectWifi();
  getip();
  esp.println("AT+CIPMUX=1");
  delay(1000);
  esp.println("AT+CIPSERVER=1,8080");
  delay(1000);
  esp.println("AT+CIPSTO=0");
}

void connectWifi() 
{
  String cmd = "AT+CWJAP=\"" +ssid+"\",\"" + password + "\"";
  esp.println(cmd);
  delay(5000);

  if(esp.find("OK")) 
  {
    Serial.println("Connected!");
  }
  else 
  {
    Serial.println("Cannot connect to wifi");
    connectWifi();
  }
}

void reset() 
{
  esp.println("AT+RST");
  delay(2000);
  if(esp.find("OK") )
  { 
    Serial.println("OK1");
  }
  else
  {
    Serial.println("error"); 
    Serial.println(esp.read());
    reset();
  }
}
void changemode()
{
  esp.println("AT+CWMODE=1");
  delay(2000);
  Serial.write(esp.read());
  }

 
  void getip()
  {
    esp.println("AT+CIFSR");
    delay(2000);
    while(esp.available())
    Serial.write(esp.read());  //在串列埠監控視窗顯示 ESP8266 的回應字元
    }

void loop()
{
     if(esp.available())
     {
      receivedata = esp.readString();
      Serial.println(receivedata);
     }
      
}


