#include <Wire.h>
#include <Adafruit_MPU6050.h>
#include <Adafruit_Sensor.h>
#include <WiFi.h>
#include <HTTPClient.h>
#include <Arduino_JSON.h>

const char* ssid = "Integrador";
const char* password = "Sorochin";
const char* serverName = "http://192.168.174.18:8080/tests";

// Variables del botón
const int buttonPin = 4;  // puerto D4
bool isCapturing = false;
unsigned long lastDebounceTime = 0;
unsigned long debounceDelay = 50;

// Variables del acelerómetro
Adafruit_MPU6050 mpu;
const int arrayLength = 20;
int sampleIndex = 0;
JSONVar samplesArray;

void setup() {
  Serial.begin(115200);
  pinMode(buttonPin, INPUT_PULLUP);  // Configurar el pin del botón como entrada con pull-up interno

  WiFi.mode(WIFI_STA);
  initWiFi();

  if (!mpu.begin()) {
    Serial.println("Error al inicializar el MPU6050");
    while (1)
      ;
  }
  Serial.println("MPU6050 inicializado");
}

void loop() {
  int buttonState = digitalRead(buttonPin);
  if (buttonState == LOW && (millis() - lastDebounceTime) > debounceDelay) {
    lastDebounceTime = millis();
    isCapturing = !isCapturing;
    if (!isCapturing) {
      // Enviar los datos al backend
      String json = JSON.stringify(samplesArray);
      Serial.println("Datos enviados al servidor:");
      Serial.println(json);  // Imprimir los datos JSON por la consola antes de enviarlos
      POSTRequest(json);
      samplesArray = JSONVar();  // Resetear el array de muestras
      sampleIndex = 0;
    }
  }

  if (isCapturing) {
    // Leer datos del acelerómetro
    sensors_event_t a, g, temp;
    mpu.getEvent(&a, &g, &temp);

    // Almacenar los datos en formato JSON
    JSONVar sample;
    sample["time"] = millis();
    sample["xPosition"] = a.acceleration.x;
    sample["yPosition"] = a.acceleration.y;
    sample["zPosition"] = a.acceleration.z;
    samplesArray[sampleIndex++] = sample;

    // Imprimir los datos por la consola
    Serial.print("Tiempo: ");
    Serial.print(sample["time"]);
    Serial.print(", X: ");
    Serial.print(sample["xPosition"]);
    Serial.print(", Y: ");
    Serial.print(sample["yPosition"]);
    Serial.print(", Z: ");
    Serial.println(sample["zPosition"]);

    delay(30);  // Ajusta el intervalo de muestreo según sea necesario
  }
}

void initWiFi() {
  WiFi.begin(ssid, password);
  Serial.print("Conectando a WiFi ..");
  while (WiFi.status() != WL_CONNECTED) {
    Serial.print('.');
    delay(1000);
  }
  Serial.println("Conectado!!");
  Serial.println(WiFi.localIP());
}

void POSTRequest(String json) {
  HTTPClient http;
  http.begin(serverName);
  http.addHeader("Content-Type", "application/json");
  int httpResponseCode = http.POST(json);

  if (httpResponseCode > 0) {
    Serial.print("Código de respuesta HTTP: ");
    Serial.println(httpResponseCode);
    String payload = http.getString();
    Serial.println(payload);
  } else {
    Serial.print("Código de error: ");
    Serial.println(httpResponseCode);
  }
  http.end();
}