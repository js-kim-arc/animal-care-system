# 도메인 모델 요약
---
## (추가) Food (enum)
먹이 종류를 모델링한다.

### 값
- `MEAT` : 육류
- `FISH` : 어류
- `PLANT` : 식물
- `NUT` : 견과류

### 규칙
- 사용자는 먹이 종류를 선택해서 제공한다.
- 동물은 허용된 먹이만 받아야 한다. (부적절한 먹이는 경고 + 효과 무효)

---
## Animal
모든 동물의 공통 행동을 담는다.

### 속성
- `name` : 동물 이름
- `age` : 동물 나이
- `hunger` : 배고픔 수치
- `happiness` : 행복도

### 행위
- 정체성/표현
  - `getGroup()` : 큰 분류(조류/포유류/파충류)
  - `getType()` : 구체 종류(강아지/독수리/사자 등)
  - `summary()` : 목록 표시용 문자열
  - `status()` : 상태 출력 문자열
- 상태 변화(운영 핵심)
  - `feed(Food food)` : 기본 먹이주기(기본 감소량)
  - `feed(Food food, int hungerDecrease)` : 감소량 주입(사육사 효과 반영)
  - `play()` : 기본 놀아주기(기본 증가량)
  - `play(int happinessIncrease)` : 증가량 주입(사육사 효과 반영)
- 반응/특성
  - `makeSound()` : 울음소리(구체 동물마다 다름)
- 먹이 규칙
  - `getAllowedFoods()` : 허용 먹이 목록 제공
  - `canEat(Food)` : 먹이 적합 판정

### 규칙
- 이름은 비어 있으면 안 된다.
- 나이는 0 이상이어야 한다.
- 배고픔 수치는 0 미만으로 내려갈 수 없다.
- 행복도는 100 초과할 수 없다.
- 부적절한 먹이 제공 시 경고 메시지 출력 + 배고픔 감소 무효 처리.
- 울음소리는 구체 동물마다 다르게 구현된다.

### 검증
- `name == null` 금지
- `name.isBlank()` 금지
- `age < 0` 금지

---

## (추가) Bird / Mammal / Reptile
`Animal` 아래의 중간 분류(추상 클래스)

### 역할
- 구체 동물은 분류를 직접 관리하지 않고 상속으로 자동 결정된다.
- `getGroup()`을 고정적으로 제공한다.

### 규칙
- `Bird` → 항상 "조류"
- `Mammal` → 항상 "포유류"
- `Reptile` → 항상 "파충류"

---
## 4) Species (구체 동물들)
구체 동물은 `getType()` / `makeSound()` / `getAllowedFoods()`를 고정적으로 책임진다.  
(필요 시 능력 인터페이스도 조합해서 구현한다)

### 예시
- `Dog`, `Cat` (포유류)
- `Eagle`, `Penguin` (조류)
- `Lion`, `Elephant` (포유류)
- `Snake`, `Turtle` (파충류)

### 규칙
- 타입/울음/허용 먹이는 구현체가 책임진다.
- 생성 시 `Animal`의 공통 검증을 그대로 따른다.

### 규칙
- 타입/울음소리는 구현체가 고정적으로 책임진다.

### 검증
- 생성 시 `Animal`의 공통 검증을 그대로 따름

---
## (추가) 능력 인터페이스 (Flyable / Swimmable 등)
동물의 “고유 능력”을 상속이 아니라 **조합**으로 붙이기 위한 규칙

### 대상
- `Flyable` : `fly()`를 수행할 수 있는 동물
- `Swimmable` : `swim()`을 수행할 수 있는 동물

### 규칙
- 능력은 "분류"가 아니라 "행동"이므로 인터페이스로 분리한다.
- 구체 동물은 필요한 능력만 구현한다. (예: Eagle=Flyable, Penguin=Swimmable)
- 능력을 구현하지 않은 동물은 능력 실행 메뉴에서 안내 메시지가 출력된다.

### 검증/판정(실행 시점)
- 선택된 동물이 해당 인터페이스를 구현했는지 확인 후 실행한다.
  - 구현 O → 능력 실행
  - 구현 X → "특별 능력이 없습니다" 안내
  - 
---

## 8) Zoo (관리자/운영자)
동물/사육사 목록을 관리하고, 선택-실행 흐름과 통계를 제공한다.

### 속성
- `animals : ArrayList<Animal>`
- `keepers : ArrayList<ZooKeeper>` (사육사 “세팅”이 아니라 “목록 관리/선택”)

### 행위
- 동물 관리
  - `addAnimal(Animal)`
  - `showAnimals()`
  - `getAnimal(index)`
- 사육사 관리
  - `addKeeper(ZooKeeper)`
  - `showKeepers()`
  - `getKeeper(index)`
- 상호작용(사육사 효과 반영)
  - `feedAnimal(index, Food, keeper)`
  - `playWithAnimal(index, keeper)`
- 상태/특성
  - `showAnimalStatus(index)`
  - `hearAnimalSound(index)`
- 통계/요약
  - `countByType()` : 종류별 개체 수
  - `averageHappiness()` : 평균 행복도
  - `hungryAnimals(threshold)` : 배고픈 동물 목록
  - `printSummary(threshold)` : 운영 요약 출력

### 규칙
- 목록 출력은 번호가 1부터 시작한다.
- 잘못된 번호 선택은 허용하지 않는다(예외/안내).
- `threshold` 이하를 배고픈 상태로 판정한다.
- 요약 출력은 운영자가 한눈에 보도록 구성한다.
---

## ZooKeeper- 사육사
“관리하다”를 모델링

### 속성
- `name` : 사육사 이름
- `specialty` : 전문 분야

### 행위
- `isSpecializedFor(Animal)` : 해당 동물이 전문 분야에 해당하는지 판정한다.
- (간접 효과) 전문 분야가 맞으면 먹이/놀이 효과가 증가한다.

### 규칙
- 전문 분야가 맞으면
  - 먹이 효과(배고픔 감소량) 증가
  - 놀이 효과(행복도 증가량) 증가
- 전문 분야가 아니면 기본 효과(default)로 동작

---

## KeeperSpecialty (enum)
사육사 전문 분야를 고정 값으로 관리한다.

- `BIRD` : 조류 전문
- `LARGE_ANIMAL` : 대형 동물 전문(예: 사자/코끼리)
- `REPTILE` : 파충류 전문

---

# 책임 분리

---

## Main (UI)
### 책임
- 사용자 입력 받기
- 메뉴 출력
- 동물/사육사 선택값을 받아 `Zoo`에 요청
- “능력 실행”에서 인터페이스 구현 여부로 실행/안내

결론: **입력/출력(UI) 책임**

---

## Animal + Group + Species + Ability
### 책임
- 상태/규칙/행동을 스스로 관리한다.
- 먹이 적합 판정 및 상태 변화의 안전장치(최소/최대)를 보장한다.
- 울음/능력 등 “종별 특성”을 구현한다.

결론: **도메인 책임**

---

## Zoo (+ ZooKeeper)
### 책임
- 동물/사육사 컬렉션을 보관하고 관리한다.
- 선택-실행 흐름(먹이/놀이/상태/울음/통계)을 연결한다.
- 전문 분야에 따른 효과 차이를 적용한다.
