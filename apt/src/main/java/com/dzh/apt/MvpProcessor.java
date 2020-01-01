package com.dzh.apt;

import com.google.auto.service.AutoService;

import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.lang.model.element.TypeElement;

@AutoService(Process.class)
@SupportedAnnotationTypes({"com.dzh.annotation.MvpEmptyViewFactory"})
public class MvpProcessor extends AbstractProcessor
{
    public Filer mFiler;
    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv)
    {
        mFiler = processingEnv.getFiler();  // processingEnv 是父类的一个 field
        new MvpEmptyViewProcessor().process(roundEnv,this);
        return true;
    }
}
