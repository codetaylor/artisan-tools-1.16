/**
 * This function is called by Forge before any minecraft classes are loaded to
 * setup the coremod.
 *
 * @return {object} All the transformers of this coremod.
 * @see https://github.com/Cadiboo/NoCubes/blob/1.14.x/src/main/resources/nocubes-transformer.js
 * @see https://cadiboo.github.io/tutorials/1.14.4/forge/99.99-coremod/
 */
function initializeCoreMod() {
  /*Class/Interface*/ Opcodes = Java.type("org.objectweb.asm.Opcodes");

  /*Class*/ InsnList = Java.type("org.objectweb.asm.tree.InsnList");

  /*Class*/ AbstractInsnNode = Java.type("org.objectweb.asm.tree.AbstractInsnNode");
  /*Class*/ VarInsnNode = Java.type("org.objectweb.asm.tree.VarInsnNode");
  /*Class*/ MethodInsnNode = Java.type("org.objectweb.asm.tree.MethodInsnNode");

  LABEL = AbstractInsnNode.LABEL;

  return {
    "MinecraftServer#func_240772_a_": {
      target: {
        type: "METHOD",
        class: "net.minecraft.server.MinecraftServer",
        methodName: "func_240772_a_",
        methodDesc: "(Lnet/minecraft/resources/ResourcePackList;Lnet/minecraft/util/datafix/codec/DatapackCodec;Z)Lnet/minecraft/util/datafix/codec/DatapackCodec;"
      },
      transformer: function (methodNode) {
        inject(methodNode.instructions);
        return methodNode;
      }
    }
  };
}

// 1) Find the first label
// 2) Inject after the label

// Java
/*
public static DatapackCodec func_240772_a_(ResourcePackList p_240772_0_, DatapackCodec p_240772_1_, boolean p_240772_2_) {
	// ********************************************************** BEGIN **********************************************************
	com.codetaylor.mc.artisantools.ArtisanToolsMod.onMinecraftServer$func_240772_a_(p_240772_0_);
    // **********************************************************  END  **********************************************************
    net.minecraftforge.fml.packs.ResourcePackLoader.loadResourcePacks(p_240772_0_, net.minecraftforge.fml.server.ServerLifecycleHooks::buildPackFinder);
*/

// Bytecode
/*
  public static func_240772_a_(Lnet/minecraft/resources/ResourcePackList;Lnet/minecraft/util/datafix/codec/DatapackCodec;Z)Lnet/minecraft/util/datafix/codec/DatapackCodec;
   L0
	// ********************************************************** BEGIN **********************************************************
	ALOAD 0
	INVOKESTATIC com/codetaylor/mc/artisantools/ArtisanToolsMod.onMinecraftServer$func_240772_a_ (Lnet/minecraft/resources/ResourcePackList;)V
	// **********************************************************  END  **********************************************************
    LINENUMBER 1412 L0
    ALOAD 0
    INVOKEDYNAMIC apply()Ljava/util/function/BiFunction; [
      // handle kind 0x6 : INVOKESTATIC
      java/lang/invoke/LambdaMetafactory.metafactory(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/String;Ljava/lang/invoke/MethodType;Ljava/lang/invoke/MethodType;Ljava/lang/invoke/MethodHandle;Ljava/lang/invoke/MethodType;)Ljava/lang/invoke/CallSite;
      // arguments:
      (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;,
      // handle kind 0x6 : INVOKESTATIC
      net/minecraftforge/fml/server/ServerLifecycleHooks.buildPackFinder(Ljava/util/Map;Ljava/util/function/BiConsumer;)Lnet/minecraftforge/fml/packs/ResourcePackLoader$IPackInfoFinder;,
      (Ljava/util/Map;Ljava/util/function/BiConsumer;)Lnet/minecraftforge/fml/packs/ResourcePackLoader$IPackInfoFinder;
    ]
    INVOKESTATIC net/minecraftforge/fml/packs/ResourcePackLoader.loadResourcePacks (Lnet/minecraft/resources/ResourcePackList;Ljava/util/function/BiFunction;)V
*/

function inject(instructions) {

    var arrayLength = instructions.size();
    var firstLabel;

    for (var i = 0; i < arrayLength; ++i) {
        var instruction = instructions.get(i);
        if (instruction.getType() == LABEL) {
            firstLabel = instruction;
            print('Found injection point at label: ' + instruction);
            break;
        }
    }

    if (!firstLabel) {
        throw "Error: Couldn't find injection point! " + instructions
    }

	var toInject = new InsnList();

	toInject.add(new VarInsnNode(Opcodes.ALOAD, 0)); // p_240772_0_
	toInject.add(new MethodInsnNode(
		//int opcode
		Opcodes.INVOKESTATIC,
		//String owner
		"com/codetaylor/mc/artisantools/ArtisanToolsMod",
		//String name
		"onMinecraftServer$func_240772_a_",
		//String descriptor
		"(Lnet/minecraft/resources/ResourcePackList;)V",
		//boolean isInterface
		false
	));

	instructions.insert(firstLabel, toInject);
}