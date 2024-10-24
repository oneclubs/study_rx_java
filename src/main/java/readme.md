
# �꾨줈�앺듃 援ъ“

�꾨줈�앺듃 援ъ“�� �ㅼ쓬怨� 媛숈씠 �ㅼ젙�⑸땲��:

```
root-project
|-- build.gradle
|-- settings.gradle
|-- submodules
    |-- annotation-processor
        |-- build.gradle
        |-- src/main/java/...
    |-- app (for testing)
        |-- build.gradle
        |-- src/main/java/...
```

# �대끂�뚯씠�� �좎뼵

`annotation-processor` �쒕툕紐⑤뱢�� `LazySingleton`�대씪�� �대끂�뚯씠�섏쓣 �앹꽦�⑸땲��.

```java
package com.example.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.SOURCE)
public @interface LazySingleton {
    String instanceMethodName() default "getInstance";
    String accessModifier() default "public";
    boolean createInstanceMethod() default true;
}
```

# �대끂�뚯씠�� �꾨줈�몄꽌

�댁젣 Bill Pugh 諛⑹떇�� �깃��� �⑦꽩�� �대끂�뚯씠�� �띿꽦�� 留욊쾶 �앹꽦�섎뒗 �대끂�뚯씠�� �꾨줈�몄꽌瑜� �묒꽦�⑸땲��. 肄붾뱶瑜� �앹꽦�섍린 �꾪빐 `Filer` API瑜� �ъ슜�� �� �덉뒿�덈떎.

```java
package com.example.processor;

import com.example.annotations.LazySingleton;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeSpec;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;
import java.io.IOException;
import java.util.Set;

@SupportedAnnotationTypes("com.example.annotations.LazySingleton")
@SupportedSourceVersion(SourceVersion.RELEASE_8)
public class LazySingletonProcessor extends AbstractProcessor {

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        for (Element element : roundEnv.getElementsAnnotatedWith(LazySingleton.class)) {
            if (element.getKind() != ElementKind.CLASS) {
                continue;
            }

            TypeElement typeElement = (TypeElement) element;
            LazySingleton annotation = typeElement.getAnnotation(LazySingleton.class);

            // �앹꽦�� 泥댄겕
            for (Element enclosedElement : typeElement.getEnclosedElements()) {
                if (enclosedElement.getKind() == ElementKind.CONSTRUCTOR) {
                    throw new IllegalStateException("Class with @LazySingleton cannot have constructors.");
                }
            }

            // �대끂�뚯씠�� 媛� 異붿텧
            String instanceMethodName = annotation.instanceMethodName();
            String accessModifier = annotation.accessModifier();
            boolean createInstanceMethod = annotation.createInstanceMethod();

            // 吏��� 珥덇린�붾� �꾪븳 �대� static �대옒�� �앹꽦
            TypeSpec singletonHelper = TypeSpec.classBuilder("SingletonHelper")
                    .addModifiers(Modifier.PRIVATE, Modifier.STATIC)
                    .addField(typeElement.getSimpleName().toString(), "INSTANCE", Modifier.PRIVATE, Modifier.STATIC, Modifier.FINAL)
                    .initializer("new " + typeElement.getSimpleName().toString() + "()")
                    .build();

            // getInstance 硫붿냼�� �앹꽦
            MethodSpec.Builder getInstanceBuilder = MethodSpec.methodBuilder(instanceMethodName)
                    .addModifiers(parseModifier(accessModifier))
                    .returns(typeElement.asType())
                    .addStatement("return SingletonHelper.INSTANCE");

            MethodSpec getInstanceMethod = getInstanceBuilder.build();

            // �대옒�� 鍮뚮뱶
            TypeSpec.Builder classBuilder = TypeSpec.classBuilder(typeElement.getSimpleName().toString())
                    .addModifiers(Modifier.PUBLIC)
                    .addType(singletonHelper);

            if (createInstanceMethod) {
                classBuilder.addMethod(getInstanceMethod);
            }

            TypeSpec singletonClass = classBuilder.build();

            // �앹꽦�� �대옒�ㅻ� �뚯씪�� �묒꽦
            JavaFile javaFile = JavaFile.builder(processingEnv.getElementUtils().getPackageOf(typeElement).toString(), singletonClass)
                    .build();

            try {
                javaFile.writeTo(processingEnv.getFiler());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return true;
    }

    private Modifier parseModifier(String modifier) {
        switch (modifier.toLowerCase()) {
            case "private":
                return Modifier.PRIVATE;
            case "protected":
                return Modifier.PROTECTED;
            default:
                return Modifier.PUBLIC;
        }
    }
}
```

# Gradle �ㅼ젙

`annotation-processor` �쒕툕紐⑤뱢�먯꽌 �꾩슂�� annotation processing�� �꾪븳 醫낆냽�깆쓣 �ㅼ젙�⑸땲��.

```groovy
plugins {
    id 'java'
}

dependencies {
    implementation 'com.squareup:javapoet:1.13.0' // 肄붾뱶 �앹꽦�� �꾪븳 �쇱씠釉뚮윭由�
}
```

`app` 紐⑤뱢(�뱀� �뚯뒪�명븷 紐⑤뱢)�� `build.gradle`�먮뒗 �ㅼ쓬怨� 媛숈씠 �ㅼ젙�⑸땲��.

```groovy
dependencies {
    annotationProcessor project(':submodules:annotation-processor')
    implementation project(':submodules:annotation-processor')
}
```

# �대끂�뚯씠�� �꾨줈�몄꽌 �뚯뒪��

`app` 紐⑤뱢�� �대옒�ㅻ� 留뚮뱾怨� `@LazySingleton` �대끂�뚯씠�섏쓣 遺숈엯�덈떎.

```java
package com.example.app;

import com.example.annotations.LazySingleton;

@LazySingleton
public class MySingleton {
    // �꾨줈�몄꽌媛� Bill Pugh �깃��� 援ы쁽 肄붾뱶瑜� �앹꽦
}
```

�꾨줈�앺듃瑜� 而댄뙆�쇳븯硫� �꾨줈�몄꽌媛� �ㅼ쓬怨� 媛숈� 肄붾뱶瑜� �앹꽦�⑸땲��.

```java
package com.example.app;

public class MySingleton {
    private static class SingletonHelper {
        private static final MySingleton INSTANCE = new MySingleton();
    }

    public static MySingleton getInstance() {
        return SingletonHelper.INSTANCE;
    }
}
```

# �ㅻ쪟 泥섎━

`MySingleton` �대옒�ㅼ뿉 而ㅼ뒪�� �앹꽦�먮� 異붽��섎㈃ �꾨줈�몄꽌媛� 而댄뙆�� 以묒뿉 �ㅻ쪟瑜� 諛쒖깮�쒗궎硫�, �대뒗 �깃��� �대옒�ㅼ뿉�� �앹꽦�먭� �덉슜�섏� �딅뒗�ㅻ뒗 洹쒖튃�� 媛뺤젣�섎뒗 寃껋엯�덈떎.

```java
@LazySingleton
public class MySingleton {
    public MySingleton() { // 而댄뙆�� �ㅻ쪟 諛쒖깮
    }
}
```

�� 怨쇱젙�� �듯빐, `getInstance` 硫붿냼�쒖쓽 �대쫫怨� �묎렐�쒖뼱�먮� 而ㅼ뒪�곕쭏�댁쭠�� �� �덉쑝硫�, 異붽��곸씤 洹쒖튃�� �곸슜�섎뒗 吏��� 珥덇린�� �깃��ㅼ쓣 �앹꽦�� �� �덉뒿�덈떎.
