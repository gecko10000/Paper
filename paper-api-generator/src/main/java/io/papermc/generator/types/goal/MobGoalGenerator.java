package io.papermc.generator.types.goal;

import com.destroystokyo.paper.entity.RangedEntity;
import com.destroystokyo.paper.entity.ai.GoalKey;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.FieldSpec;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.ParameterSpec;
import com.squareup.javapoet.ParameterizedTypeName;
import com.squareup.javapoet.TypeName;
import com.squareup.javapoet.TypeSpec;
import com.squareup.javapoet.TypeVariableName;
import com.squareup.javapoet.WildcardTypeName;
import io.github.classgraph.ClassGraph;
import io.github.classgraph.ScanResult;
import io.papermc.generator.types.SimpleGenerator;
import io.papermc.generator.utils.Annotations;
import io.papermc.generator.utils.ClassHelper;
import io.papermc.generator.utils.Formatting;
import io.papermc.generator.utils.Javadocs;
import java.lang.reflect.ParameterizedType;
import java.util.Comparator;
import java.util.List;
import javax.lang.model.element.Modifier;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.GoalSelector;
import net.minecraft.world.entity.ai.goal.WrappedGoal;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.AbstractHorse;
import org.bukkit.entity.Blaze;
import org.bukkit.entity.Cat;
import org.bukkit.entity.Creature;
import org.bukkit.entity.Dolphin;
import org.bukkit.entity.Drowned;
import org.bukkit.entity.Enderman;
import org.bukkit.entity.Evoker;
import org.bukkit.entity.Fish;
import org.bukkit.entity.Fox;
import org.bukkit.entity.Ghast;
import org.bukkit.entity.Llama;
import org.bukkit.entity.Mob;
import org.bukkit.entity.Monster;
import org.bukkit.entity.Panda;
import org.bukkit.entity.Parrot;
import org.bukkit.entity.PigZombie;
import org.bukkit.entity.PolarBear;
import org.bukkit.entity.Rabbit;
import org.bukkit.entity.Raider;
import org.bukkit.entity.Ravager;
import org.bukkit.entity.Shulker;
import org.bukkit.entity.Silverfish;
import org.bukkit.entity.SkeletonHorse;
import org.bukkit.entity.Slime;
import org.bukkit.entity.Spellcaster;
import org.bukkit.entity.Spider;
import org.bukkit.entity.Squid;
import org.bukkit.entity.Tameable;
import org.bukkit.entity.TraderLlama;
import org.bukkit.entity.Turtle;
import org.bukkit.entity.Vex;
import org.bukkit.entity.Vindicator;
import org.bukkit.entity.WanderingTrader;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.framework.qual.DefaultQualifier;
import org.jetbrains.annotations.Nullable;

import static javax.lang.model.element.Modifier.FINAL;
import static javax.lang.model.element.Modifier.PUBLIC;
import static javax.lang.model.element.Modifier.STATIC;

@DefaultQualifier(NonNull.class)
public class MobGoalGenerator extends SimpleGenerator {

    private static final String CLASS_HEADER = Javadocs.getVersionDependentClassHeader("Mob Goals");

    private static final DeprecatedGoal[] DEPRECATED_GOALS = {
        //<editor-fold defaultstate="collapsed" desc="legacy goals">
        new DeprecatedGoal(Vindicator.class, "vindicator_melee_attack", null, "1.20.2"),
        new DeprecatedGoal(Ravager.class, "ravager_melee_attack", null, "1.20.2"),
        new DeprecatedGoal(Rabbit.class, "evil_rabbit_attack", null, "1.20.2"),
        new DeprecatedGoal(PigZombie.class, "anger", "1.21", "1.16"),
        new DeprecatedGoal(PigZombie.class, "anger_other", "1.21", "1.16"),
        new DeprecatedGoal(Blaze.class, "blaze_fireball", "1.21", null),
        new DeprecatedGoal(Cat.class, "tempt_chance", "1.21", null),
        new DeprecatedGoal(Dolphin.class, "dolphin_play_with_items", "1.21", null),
        new DeprecatedGoal(Drowned.class, "drowned_goto_beach", "1.21", null),
        new DeprecatedGoal(Creature.class, "drowned_goto_water", "1.21", null),
        new DeprecatedGoal(Enderman.class, "enderman_pickup_block", "1.21", null),
        new DeprecatedGoal(Enderman.class, "enderman_place_block", "1.21", null),
        new DeprecatedGoal(Enderman.class, "player_who_looked_at_target", "1.21", null),
        new DeprecatedGoal(Evoker.class, "evoker_cast_spell", "1.21", null),
        new DeprecatedGoal(Fox.class, "fox_defend_trusted", "1.21", null),
        new DeprecatedGoal(Fox.class, "fox_faceplant", "1.21", null),
        new DeprecatedGoal(Fox.class, "fox_perch_and_search", "1.21", null),
        new DeprecatedGoal(Fox.class, "fox_sleep", "1.21", null),
        new DeprecatedGoal(Fox.class, "fox_seek_shelter", "1.21", null),
        new DeprecatedGoal(Fox.class, "fox_stalk_prey", "1.21", null),
        new DeprecatedGoal(Ghast.class, "ghast_attack_target", "1.21", null),
        new DeprecatedGoal(Ghast.class, "ghast_idle_move", "1.21", null),
        new DeprecatedGoal(Ghast.class, "ghast_move_towards_target", "1.21", null),
        new DeprecatedGoal(Spellcaster.class, "spellcaster_cast_spell", "1.21", null),
        new DeprecatedGoal(TraderLlama.class, "llamatrader_defended_wandering_trader", "1.21", null),
        new DeprecatedGoal(Panda.class, "panda_hurt_by_target", "1.21", null),
        new DeprecatedGoal(PolarBear.class, "polarbear_attack_players", "1.21", null),
        new DeprecatedGoal(PolarBear.class, "polarbear_hurt_by", "1.21", null),
        new DeprecatedGoal(PolarBear.class, "polarbear_melee", "1.21", null),
        new DeprecatedGoal(PolarBear.class, "polarbear_panic", "1.21", null),
        new DeprecatedGoal(Rabbit.class, "eat_carrots", "1.21", null),
        new DeprecatedGoal(Rabbit.class, "killer_rabbit_melee_attack", "1.21", null),
        new DeprecatedGoal(Rabbit.class, "rabbit_avoid_target", "1.21", null),
        new DeprecatedGoal(Raider.class, "raider_hold_ground", "1.21", null),
        new DeprecatedGoal(Raider.class, "raider_obtain_banner", "1.21", null),
        new DeprecatedGoal(Shulker.class, "shulker_defense", "1.21", null),
        new DeprecatedGoal(Shulker.class, "shulker_nearest", "1.21", null),
        new DeprecatedGoal(Silverfish.class, "silverfish_hide_in_block", "1.21", null),
        new DeprecatedGoal(Silverfish.class, "silverfish_wake_others", "1.21", null),
        new DeprecatedGoal(Slime.class, "slime_idle", "1.21", null),
        new DeprecatedGoal(Slime.class, "slime_nearest_player", "1.21", null),
        new DeprecatedGoal(Slime.class, "slime_random_jump", "1.21", null),
        new DeprecatedGoal(Spider.class, "spider_melee_attack", "1.21", null),
        new DeprecatedGoal(Spider.class, "spider_nearest_attackable_target", "1.21", null),
        new DeprecatedGoal(Squid.class, "squid", "1.21", null),
        new DeprecatedGoal(Turtle.class, "turtle_goto_water", "1.21", null),
        new DeprecatedGoal(Turtle.class, "turtle_tempt", "1.21", null),
        new DeprecatedGoal(Vex.class, "vex_copy_target_of_owner", "1.21", null),
        new DeprecatedGoal(WanderingTrader.class, "villagertrader_wander_to_position", "1.21", null),
        new DeprecatedGoal(RangedEntity.class, "arrow_attack", "1.21", null),
        new DeprecatedGoal(Creature.class, "avoid_target", "1.21", null),
        new DeprecatedGoal(Monster.class, "bow_shoot", "1.21", null),
        new DeprecatedGoal(Creature.class, "breath", "1.21", null),
        new DeprecatedGoal(Cat.class, "cat_sit_on_bed", "1.21", null),
        new DeprecatedGoal(Monster.class, "crossbow_attack", "1.21", null),
        new DeprecatedGoal(Mob.class, "door_open", "1.21", null),
        new DeprecatedGoal(Mob.class, "eat_tile", "1.21", null),
        new DeprecatedGoal(Fish.class, "fish_school", "1.21", null),
        new DeprecatedGoal(Mob.class, "follow_entity", "1.21", null),
        new DeprecatedGoal(SkeletonHorse.class, "horse_trap", "1.21", null),
        new DeprecatedGoal(Creature.class, "hurt_by_target", "1.21", null),
        new DeprecatedGoal(Cat.class, "jump_on_block", "1.21", null),
        new DeprecatedGoal(Mob.class, "leap_at_target", "1.21", null),
        new DeprecatedGoal(Llama.class, "llama_follow", "1.21", null),
        new DeprecatedGoal(Creature.class, "move_towards_target", "1.21", null),
        new DeprecatedGoal(Mob.class, "nearest_attackable_target", "1.21", null),
        new DeprecatedGoal(Raider.class, "nearest_attackable_target_witch", "1.21", null),
        new DeprecatedGoal(Creature.class, "nearest_village", "1.21", null),
        new DeprecatedGoal(Tameable.class, "owner_hurt_by_target", "1.21", null),
        new DeprecatedGoal(Tameable.class, "owner_hurt_target", "1.21", null),
        new DeprecatedGoal(Parrot.class, "perch", "1.21", null),
        new DeprecatedGoal(Raider.class, "raid", "1.21", null),
        new DeprecatedGoal(Creature.class, "random_fly", "1.21", null),
        new DeprecatedGoal(Mob.class, "random_lookaround", "1.21", null),
        new DeprecatedGoal(Creature.class, "random_stroll_land", "1.21", null),
        new DeprecatedGoal(Creature.class, "random_swim", "1.21", null),
        new DeprecatedGoal(Tameable.class, "random_target_non_tamed", "1.21", null),
        new DeprecatedGoal(Tameable.class, "sit", "1.21", null),
        new DeprecatedGoal(Creature.class, "stroll_village", "1.21", null),
        new DeprecatedGoal(AbstractHorse.class, "tame", "1.21", null),
        new DeprecatedGoal(Creature.class, "water", "1.21", null),
        new DeprecatedGoal(Dolphin.class, "water_jump", "1.21", null),
        new DeprecatedGoal(Creature.class, "stroll_village_golem", "1.21", null),
        new DeprecatedGoal(Mob.class, "universal_anger_reset", "1.21", null)
        //</editor-fold>
    };

    public MobGoalGenerator(final String className, final String pkg) {
        super(className, pkg);
    }

    @Override
    protected TypeSpec getTypeSpec() {
        TypeVariableName type = TypeVariableName.get("T", Mob.class);
        TypeSpec.Builder typeBuilder = TypeSpec.interfaceBuilder(this.className)
            .addSuperinterface(ParameterizedTypeName.get(ClassName.get(com.destroystokyo.paper.entity.ai.Goal.class), type))
            .addModifiers(PUBLIC)
            .addTypeVariable(type)
            .addAnnotations(Annotations.CLASS_HEADER)
            .addJavadoc(CLASS_HEADER);

        TypeName mobType = ParameterizedTypeName.get(ClassName.get(Class.class), type)
            .annotated(Annotations.NOT_NULL);
        TypeName keyType = TypeName.get(String.class)
            .annotated(Annotations.NOT_NULL);

        ParameterSpec keyParam = ParameterSpec.builder(keyType, "key", FINAL).build();
        ParameterSpec typeParam = ParameterSpec.builder(mobType, "type", FINAL).build();
        MethodSpec.Builder createMethod = MethodSpec.methodBuilder("create")
            .addModifiers(Modifier.PRIVATE, STATIC)
            .addParameter(keyParam)
            .addParameter(typeParam)
            .addCode("return $T.of($N, $T.minecraft($N));", GoalKey.class, typeParam, NamespacedKey.class, keyParam)
            .addTypeVariable(type)
            .returns(ParameterizedTypeName.get(ClassName.get(GoalKey.class), type).annotated(Annotations.NOT_NULL));

        List<Class<Goal>> classes;
        try (ScanResult scanResult = new ClassGraph().enableAllInfo().whitelistPackages("net.minecraft").scan()) {
            classes = scanResult.getSubclasses(Goal.class.getName()).loadClasses(Goal.class);
        }

        List<VanillaGoal> vanillaGoals = classes.stream()
            .filter(clazz -> !java.lang.reflect.Modifier.isAbstract(clazz.getModifiers()))
            .filter(clazz -> !clazz.isAnonymousClass() || ClassHelper.getRootClass(clazz) != GoalSelector.class)
            .filter(clazz -> !WrappedGoal.class.equals(clazz)) // TODO - properly fix
            .map(goalClass -> new VanillaGoal(goalClass, MobGoalNames.getKey(goalClass)))
            .sorted(Comparator.<VanillaGoal, String>comparing(o -> o.key().getEntityClass().getSimpleName())
                .thenComparing(vanillaGoalKey -> vanillaGoalKey.key().getNamespacedKey().getKey())
            )
            .toList();

        for (final VanillaGoal vanillaGoal : vanillaGoals) {
            GoalKey<?> goalKey = vanillaGoal.key();
            String keyPath = goalKey.getNamespacedKey().getKey();
            String fieldName = Formatting.formatPathAsField(keyPath);

            TypeName typedKey = ParameterizedTypeName.get(GoalKey.class, goalKey.getEntityClass());
            FieldSpec.Builder fieldBuilder = FieldSpec.builder(typedKey, fieldName, PUBLIC, STATIC, FINAL)
                .initializer("$N($S, $T.class)", createMethod.build(), keyPath, goalKey.getEntityClass());
            typeBuilder.addField(fieldBuilder.build());
        }

        for (final DeprecatedGoal deprecatedGoal : DEPRECATED_GOALS) {
            TypeName typedKey = ParameterizedTypeName.get(GoalKey.class, deprecatedGoal.goalClass());

            String fieldName = Formatting.formatPathAsField(deprecatedGoal.path());
            FieldSpec.Builder fieldBuilder = FieldSpec.builder(typedKey, fieldName, PUBLIC, STATIC, FINAL)
                .addAnnotation(Annotations.deprecatedVersioned(deprecatedGoal.removedVersion(), deprecatedGoal.removalVersion() != null))
                .initializer("$N($S, $T.class)", createMethod.build(), deprecatedGoal.path(), deprecatedGoal.goalClass());

            if (deprecatedGoal.removedVersion() != null) {
                fieldBuilder.addJavadoc("Removed in $L", deprecatedGoal.removedVersion());
            }
            if (deprecatedGoal.removalVersion() != null) {
                fieldBuilder.addAnnotation(Annotations.scheduledRemoval(deprecatedGoal.removalVersion()));
            }

            typeBuilder.addField(fieldBuilder.build());
        }

        return typeBuilder.addMethod(createMethod.build()).build();
    }

    record VanillaGoal(Class<? extends Goal> goalClass, GoalKey<?> key) {
    }

    record DeprecatedGoal(Class<? extends Mob> goalClass, String path, @Nullable String removalVersion,
                          @Nullable String removedVersion) {
    }
}
