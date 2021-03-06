package hb.spring.recipeapp.converters;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.databind.util.Converter;
import com.sun.istack.Nullable;
import hb.spring.recipeapp.commands.IngredientCommand;
import hb.spring.recipeapp.commands.UnitOfMeasureCommand;
import hb.spring.recipeapp.domain.Ingredient;
import lombok.Synchronized;
import org.springframework.stereotype.Component;


@Component
public class IngredientToCommand implements Converter<Ingredient, IngredientCommand> {

    private final UnitOfMeasureToCommand converter;

    public IngredientToCommand(UnitOfMeasureToCommand converter) {
        this.converter = converter;
    }

    @Synchronized
    @Nullable
    @Override
    public IngredientCommand convert(Ingredient ingredient) {
        if (ingredient == null)
            return null;

        final IngredientCommand ingredientCommand = new IngredientCommand();
        ingredientCommand.setId(ingredient.getId());
        if (ingredient.getRecipe() != null )
            ingredientCommand.setRecipeId(ingredient.getRecipe().getId());

        ingredientCommand.setAmount(ingredient.getAmount());
        ingredientCommand.setDescription(ingredient.getDescription());
        ingredientCommand.setUnitOfMeasureCommand(converter.convert(ingredient.getUom()));
        return ingredientCommand;
    }

    @Override
    public JavaType getInputType(TypeFactory typeFactory) {
        return null;
    }

    @Override
    public JavaType getOutputType(TypeFactory typeFactory) {
        return null;
    }
}
