import http from 'k6/http';
import { check, sleep } from 'k6';

// 1. Konfiguráció: Szimuláljunk 10 virtuális felhasználót, akik 20 másodpercig folyamatosan küldik a kéréseket
export const options = {
  stages: [
    { duration: '5s', target: 10 },   // 5 másodperc alatt felmentünk 10 felhasználóra (Ramp-up)
    { duration: '10s', target: 10 },  // Tartjuk a 10 felhasználós terhelést 10 másodpercig
    { duration: '5s', target: 0 },    // 5 másodperc alatt lecsengünk 0-ra (Ramp-down)
  ],
  thresholds: {
    http_req_duration: ['p(95)<400'], // A kérések 95%-ának 400 ms alatt kell lefutnia
    http_req_failed: ['rate<0.01'],   // A hibás kérések aránya 1%-nál alacsonyabb kell legyen
  },
};

// 2. A teszt logika (amit az Umbrella API-n futtatunk)
export default function () {
  const url = 'https://jsonplaceholder.typicode.com/users/1';

  // Ha volt az API-hoz hitelesítés (pl. a híres X-Umbrella-Passcode fejlécünk), azt itt is megadhatjuk:
  const params = {
    headers: {
      'Content-Type': 'application/json',
      'X-Umbrella-Passcode': '4829',
    },
  };

  // Küldünk egy GET kérést
  const res = http.get(url, params);

  // Ellenőrzések (Assertions) minden egyes kérés után
  check(res, {
    'státuszkód 200-as': (r) => r.status === 200,
    'válaszidő rendben van': (r) => r.timings.duration < 500,
  });

  sleep(1); // 1 mp pihenő két kérés között
}