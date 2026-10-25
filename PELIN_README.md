# Pelin’in Havası ☀️

Turkcell Bilişim Kampı kapsamında, kampın referans projesi temel alınarak kişiselleştirilmiş Android hava durumu uygulaması.

Şehir arayabilir, anlık hava durumunu inceleyebilir ve saatlik veya 7 günlük tahminlerle gününü planlayabilirsin.

## Özellikler

- Şehir adına göre arama
- Anlık sıcaklık ve hava durumu
- Hissedilen sıcaklık, nem ve rüzgâr hızı
- Önümüzdeki 24 saat için sıcaklık ve yağış olasılığı
- 7 günlük en düşük ve en yüksek sıcaklık tahminleri
- Favori şehirleri ekleme, çıkarma ve hızlı erişim
- Son görüntülenen beş şehir ve geçmişi temizleme
- Sistem, açık ve koyu tema seçenekleri
- Favorilerin, geçmişin ve tema seçiminin cihazda saklanması
- Hava verilerini yenileme ve verinin zamanını görüntüleme
- Yağış, hissedilen sıcaklık ve rüzgâra göre günlük plan önerileri
- Yüklenme, sonuç bulunamaması ve bağlantı hatası ekranları

## Referans Projeye Eklediğim Özellikler

Kampın şehir arama ve hava tahmini altyapısı korunarak favori şehirler, son görüntülenen şehirler, kalıcı tema tercihleri ve kişisel karşılama alanı eklendi.

Tahmin ekranına yenileme düğmesi, veri zamanı ve günlük plan önerileri eklendi. Saatlik tahminlerin seçilen şehrin yerel veri zamanına göre gösterilmesi sağlandı. Sıfır yağış olasılığı ile eksik verinin gösterimi birbirinden ayrıldı.

## Kullanılan Teknolojiler

- Kotlin
- Jetpack Compose ve Material 3
- ViewModel ve StateFlow
- Kotlin Coroutines
- Retrofit ve Gson
- Navigation Compose
- SharedPreferences

## Proje Yapısı

- `model`: Şehir ve hava tahmini modelleri
- `data`: API bağlantıları, repository sınıfları ve kişisel tercihler
- `ui`: Şehir arama, tahmin ekranları ve tema

## Çalıştırma

1. Depoyu bilgisayarına klonla.
2. Android Studio’da proje klasörünü aç.
3. Gradle JDK olarak Java 17 veya 21 seç.
4. Gerekli Android SDK 36 bileşenlerini kur ve Gradle Sync işlemini tamamla.
5. Android 8.0 (API 26) veya üzeri bir emülatör ya da cihaz seç.
6. Run düğmesine bas.

Hava verilerinin alınması için internet bağlantısı gerekir. Projenin mevcut API çağrıları için API anahtarı girilmez.

## Veri Kaynağı

Şehir arama ve hava tahminleri [Open-Meteo](https://open-meteo.com/) servisinden alınır.

Günlük plan önerileri tahmin verilerinden basit kurallarla üretilir; resmî meteoroloji uyarısı değildir.

## Geliştirilmesi Planlananlar

- Cihaz konumundan hava durumunu getirme
- İnternet yokken son kaydedilen tahmini görüntüleme
- Uygulama simgesi ve görsel tasarımın geliştirilmesi

## Kaynak ve Katkı

Bu uygulama, Turkcell Bilişim Kampı’nda kullanılan
[tc-yusufburakdemir/weather-app](https://github.com/tc-yusufburakdemir/weather-app)
referans projesi temel alınarak geliştirilmiştir.

Kişiselleştirme ve ek geliştirmeler: **Pelin Özbilgin**
