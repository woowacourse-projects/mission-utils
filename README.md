# mission-utils

[![Release](https://jitpack.io/v/woowacourse-projects/mission-utils.svg)](https://jitpack.io/#woowacourse-projects/mission-utils)

## Install

### Gradle

```groovy
repositories {
    maven { url 'https://jitpack.io' }
}

dependencies {
    implementation 'com.github.woowacourse-projects:mission-utils:1.0.0'
}
```

## Features

### Console

#### `readLine()`

줄 바꿈 문자를 제외하고 현재 줄의 나머지 부분을 반환한다.

```java
Console.readLine();
```

### Randoms

#### `pickNumberInList(List)`

목록에 있는 숫자 중 하나를 반환한다.

```java
Randoms.pickNumberInList(Arrays.asList(1, 3, 10)); // 1
Randoms.pickNumberInList(Arrays.asList(1, 3, 10)); // 10
Randoms.pickNumberInList(Arrays.asList(1, 3, 10)); // 3
```

#### `pickNumberInRange(int, int)`

숫자 범위를 지정하면 시작 또는 끝 숫자를 포함하여 범위의 숫자를 반환한다.

```java
Randoms.pickNumberInRange(1, 10); // 1
Randoms.pickNumberInRange(1, 10); // 10
Randoms.pickNumberInRange(1, 10); // 4
Randoms.pickNumberInRange(1, 10); // 5
```

#### `pickUniqueNumbersInRange(int, int, int)`

숫자 범위 내에서 지정된 개수만큼 겹치지 않는 숫자를 반환한다.

```java
Randoms.pickUniqueNumbersInRange(1, 10, 2); // [1, 2]
Randoms.pickUniqueNumbersInRange(1, 10, 5); // [1, 10, 7, 8, 5]
```

#### `shuffle(List)`

무작위로 섞인 새 목록을 반환한다.

```java
Randoms.shuffle(Arrays.asList(1, 2, 3, 4, 5)); // [2, 4, 1, 3, 5]
```

## Contributors

[<img src="https://github.com/hsik0225.png" width="50" alt="hsik0225">](https://github.com/hsik0225)
[<img src="https://github.com/Joyykim.png" width="50" alt="Joyykim">](https://github.com/Joyykim)
[<img src="https://github.com/unluckyjung.png" width="50" alt="unluckyjung">](https://github.com/unluckyjung)
