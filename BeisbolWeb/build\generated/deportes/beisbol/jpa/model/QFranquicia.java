package deportes.beisbol.jpa.model;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QFranquicia is a Querydsl query type for Franquicia
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QFranquicia extends EntityPathBase<Franquicia> {

    private static final long serialVersionUID = 1088224193L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QFranquicia franquicia = new QFranquicia("franquicia");

    public final StringPath abreviatura = createString("abreviatura");

    public final StringPath archivoEscudo = createString("archivoEscudo");

    public final QClub club;

    public final DateTimePath<java.sql.Timestamp> fechaActualizacion = createDateTime("fechaActualizacion", java.sql.Timestamp.class);

    public final SetPath<FranquiciaHistorico, QFranquiciaHistorico> franquiciaHistoricos = this.<FranquiciaHistorico, QFranquiciaHistorico>createSet("franquiciaHistoricos", FranquiciaHistorico.class, QFranquiciaHistorico.class, PathInits.DIRECT2);

    public final SetPath<FranquiciaVista, QFranquiciaVista> franquiciaVistas = this.<FranquiciaVista, QFranquiciaVista>createSet("franquiciaVistas", FranquiciaVista.class, QFranquiciaVista.class, PathInits.DIRECT2);

    public final NumberPath<Short> id = createNumber("id", Short.class);

    public final QLiga liga;

    public final StringPath nombreCompletoEs = createString("nombreCompletoEs");

    public final StringPath nombreTablasEs = createString("nombreTablasEs");

    public final QParque parque;

    public final NumberPath<Byte> versionFranquicia = createNumber("versionFranquicia", Byte.class);

    public QFranquicia(String variable) {
        this(Franquicia.class, forVariable(variable), INITS);
    }

    public QFranquicia(Path<? extends Franquicia> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QFranquicia(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QFranquicia(PathMetadata<?> metadata, PathInits inits) {
        this(Franquicia.class, metadata, inits);
    }

    public QFranquicia(Class<? extends Franquicia> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.club = inits.isInitialized("club") ? new QClub(forProperty("club"), inits.get("club")) : null;
        this.liga = inits.isInitialized("liga") ? new QLiga(forProperty("liga")) : null;
        this.parque = inits.isInitialized("parque") ? new QParque(forProperty("parque"), inits.get("parque")) : null;
    }

}

