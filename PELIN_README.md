# Pelin’in Havası

Bilişim Kampı referans uygulamasına dayanan kişisel Android projesi.
Kaynak: https://github.com/tc-yusufburakdemir/weather-app
Temel alınan commit: 884c9bf6166dfb36dd133c97879bc1965aa00544
Orijinal README ve açıklamalar korunmuştur.

## Korunan özellikler
Şehir arama, anlık sıcaklık, hissedilen sıcaklık, nem, rüzgâr,
24 saatlik ve 7 günlük tahmin, yüklenme/boş/hata ekranları,
tekrar deneme, navigasyon ve mevcut testler.

## Eklenenler
- Favori şehirleri ekle/çıkar; hızlı erişim için şehir çipleri.
- Son bakılan beş şehir ve geçmişi temizleme.
- Sistem/açık/koyu tema seçimi.
- Favoriler, geçmiş ve tema SharedPreferences ile cihazda kalıcıdır.
- Pelin karşılama alanı, kişisel uygulama adı, başlangıçta Gaziantep araması.
- Yeni aramada önceki arama iptal edilir.

## Android Studio ile açma
1. ZIP dosyasını bir klasöre çıkar.
2. Android Studio > Open ile settings.gradle.kts içeren klasörü seç.
3. Gradle Sync işlemini tamamla; gerekiyorsa SDK 36 kurulumunu kabul et.
4. API 26 veya üzeri emülatör/Android cihaz seç ve Run düğmesine bas.
5. API anahtarı gerekmez; hava verileri için internet gerekir.

## Doğrulama
Bu ortamda Gradle indirme adresine erişilemediğinden derleme ve testler
çalıştırılamadı; APK üretilmedi. Android Studio’da test ve derleme yapılmalıdır.
Komut: ./gradlew testDebugUnitTest assembleDebug

Elle kontrol: şehir ara, favoriye ekle, şehre dokunup tahminleri gör,
geri dön ve son aramayı aç, tema değiştir, uygulamayı yeniden başlatıp
tercihleri kontrol et, interneti kapatıp hata/tekrar denemeyi kontrol et.

## Değişiklikleri öğrenme sırası
1. data/PersonalSettings.kt: kalıcı tercihler.
2. MainActivity.kt: tema ve tercihlerin ekrana aktarılması.
3. ui/city/PersonalPanel.kt: karşılama, tema ve hızlı erişim.
4. CityCard.kt: favori düğmesi.
5. CityScreen.kt: şehir açarken geçmişi güncelleme.
6. CityViewModel.kt: Gaziantep başlangıcı ve eski isteği iptal etme.

## İkinci sürüm: günlük kullanıma yönelik düzeltmeler
- Tahmin ekranından yeniden veri getiren Yenile düğmesi.
- API verisinin tarihi ve şehrin yerel saati etiketi.
- Saatlik tahminler telefon yerine API current zamanından seçilir.
- Gece yarısından sonraki saatlerde tarih gösterilir.
- Yağış %0 ile bilinmeyen değer (—) ayrı gösterilir.
- Nem, rüzgâr, hissedilen sıcaklık eksikse — gösterilir.
- Saatlik/günlük veri yoksa açıklama gösterilir.
- Yağış, hissedilen sıcaklık ve rüzgârla ilgili basit plan önerileri.
- Arama simgesine basarak da arama başlatılır; boş arama internete gönderilmez.
- Yinelenen tahmin istekleri iptal edilir, bozuk tarih yanıtı hata ekranına dönüşür.
- Yeni sunum kuralları için regresyon testleri eklendi; başlangıç şehir testi güncellendi.

Sınırlar: cihaz konumu, internet yokken önbellekten tahmin, otomatik yenileme,
bildirimler ve resmî uyarı entegrasyonu henüz uygulanmadı. Öneriler yalnızca
API tahmin verilerinden hesaplanır. Verinin zamanı indirme zamanı değildir.
Saatlik liste son alınan verinin zamanını temel alır; uzun süre açık kalırsa Yenile kullanılmalıdır.
