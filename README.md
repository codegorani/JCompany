# JCompany

### tech-specific
- springboot2
- thymeleaf
- spring-security
- spring-jpa, querydsl
- spring-batch
- spring-mail
- swagger

이 프로젝트는 스프링부트2를 중점적으로 이용하는 프로젝트입니다.
Template Engine으로는 Thymeleaf를 사용하였으며 Database는 MySQL을 사용합니다.
스프링부트 2.4.0버전을 기준으로 작성하였습니다.

Jpa에서 사용하는 `Entity`와 기타 요청에 사용되는 Pojo는 각각 그 요청의 용도에 맞게 짜여집니다.

```java
//Entity
...
@Entity
public class MyEntity{
  //id ...
  
  @Column(nullable=false)
  private String lastName;
  @Column(nullable=false)
  private String firstName;
  @Enumerated(EnumType.STRING)
  @Column(nullable=false)
  private Gender gender; //Enum
  private String comment;
  ...
}
```
이러한 Entity에서 POST 요청으로 데이터를 저장하고자 할 때는
```java
@Getter
public class MyEntitySaveRequestDto{
   private String lastName;
   private String firstName;
   private Gender gender;
   
   @Builder
   public MyEntity....(String lastName, String firstName, Gender gender){
         ...
   }
}
```

GET요청으로 데이터를 요청할 때는
```java
@Getter
public class MyEntityResponseDto{
   private String name;
   private Gender gender;
   
   public MyEntityResponseDto(MyEntity entity) {
      this.name = entity.getLastName() + entity.getFirstName();
      this.gender = entity.getGender();
   }
}
```
다음과 같은 Dto 규칙을 가집니다.
