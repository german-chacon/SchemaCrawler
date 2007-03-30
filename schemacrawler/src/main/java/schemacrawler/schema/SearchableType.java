/* 
 *
 * SchemaCrawler
 * http://sourceforge.net/projects/schemacrawler
 * Copyright (c) 2000-2007, Sualeh Fatehi.
 *
 * This library is free software; you can redistribute it and/or modify it under the terms
 * of the GNU Lesser General Public License as published by the Free Software Foundation;
 * either version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY;
 * without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 * See the GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License along with this
 * library; if not, write to the Free Software Foundation, Inc., 59 Temple Place, Suite 330,
 * Boston, MA 02111-1307, USA.
 *
 */

package schemacrawler.schema;


import java.sql.DatabaseMetaData;
import java.util.EnumSet;

/**
 * An enumeration wrapper around JDBC procedure types.
 */
public enum SearchableType
{

  /** Not searchable. */
  typePredNone(DatabaseMetaData.typePredNone, "not searchable"),
  /** Only searchable with where .. like. */
  typePredChar(DatabaseMetaData.typePredChar,
    "only searchable with where .. like"),
  /** Searchable except with where .. like. */
  typePredBasic(DatabaseMetaData.typePredBasic,
    "searchable except with where .. like"),
  /** Searchable. */
  typeSearchable(DatabaseMetaData.typeSearchable, "searchable");

  /**
   * Gets the enum value from the integer.
   * 
   * @param id
   *        Id of the integer
   * @return SearchableType
   */
  public static SearchableType valueOf(final int id)
  {
    final EnumSet<SearchableType> allOf = EnumSet.allOf(SearchableType.class);
    for (final SearchableType type: allOf)
    {
      if (type.getId() == id)
      {
        return type;
      }
    }
    return null;
  }

  private final int id;
  private final String name;

  private SearchableType(final int id, final String name)
  {
    this.id = id;
    this.name = name;
  }

  /**
   * Gets the id.
   * 
   * @return id
   */
  public int getId()
  {
    return id;
  }

  /**
   * {@inheritDoc}
   * 
   * @see Object#toString()
   */
  @Override
  public String toString()
  {
    return name;
  }

}
