package k0ras1k.utils;

import gloomyfolken.hooklib.minecraft.HookLoader;
import gloomyfolken.hooklib.minecraft.PrimaryClassTransformer;

public class K0ras1KHookLoader extends HookLoader {
    @Override
    public String[] getASMTransformerClass() {
        return new String[]{PrimaryClassTransformer.class.getName()};
    }

    @Override
    public void registerHooks() {
        //регистрируем класс, где есть методы с аннотацией @Hook
        //обязательно нужно оставить название класса в виде строки, AnnotationHooks.class все сломает
        registerHookContainer("gloomyfolken.hooklib.example.AnnotationHooks");
    }


}
