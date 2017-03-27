#include <NewPing.h>

#define TRIGGER_PIN  9
#define ECHO_PIN     12
#define MAX_DISTANCE 400
int cm;

NewPing sonar(TRIGGER_PIN, ECHO_PIN, MAX_DISTANCE);


int sensorPin = A0;    // select the input pin for the potentiometer
int ledPin = 10;      // select the pin for the LED
int sensorValue = 0;  // variable to store the value coming from the sensor
int ledValue;

void setup() {
  // declare the ledPin as an OUTPUT:
  Serial.begin(9600);
  pinMode(ledPin, OUTPUT);
}

void loop() {
  // read the value from the sensor:
  sensorValue = analogRead(sensorPin);
  ledValue = map(sensorValue, 0, 1024, 0, 255);
  //Serial.println(ledValue);
  analogWrite(ledPin, ledValue);

  delay(35);
  int uS = sonar.ping();
  Serial.print("Ping: ");
  cm = sonar.convert_cm(uS); 
  Serial.print(cm);
  Serial.println("cm");
}
